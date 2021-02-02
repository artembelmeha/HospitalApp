package service;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.dto.UserDto;
import model.entity.Role;
import model.entity.User;
import org.apache.log4j.Logger;
import utils.EncryptUtil;

import java.util.List;

import static model.entity.Role.NURSE;
import static model.entity.Role.UNDEFINE;


public class UserService {

    private static MedicalCardService medicalCardService = ServiceFactory.getInstance().getCardService();

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    public long signIn(String email, String password) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            User user = userDao.findUserByEmail(email);
            if (EncryptUtil.encryptString(password).equals(user.getPassword())) {
                return user.getId();
            }
            throw new UnknownSqlException("error.wrongPassword");
        } catch (UnknownSqlException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public User getUserByEmail(String email) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.findUserByEmail(email);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public List<User> getUsersByRole(Role role) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getUsersByRole(role);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public User create(UserDto userDto) {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setRole(UNDEFINE);
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setOnTreatment(false);
        user.setPatientsNumber(0);
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.create(user);
        }
    }

    public List<User> getUsersByDoctorId(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getUserByDoctorId(id);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public User getUserById(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.findById(id);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public void assignAsNurse(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.updateUserRole(id, NURSE);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

    }

    public void assignAsDoctor(DoctorDto doctorDto) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.updateUserToDoctor(doctorDto);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    public void assignAsPatient(PatientDto patientDto) {
        if (patientDto.getCardId() == 0) {
            long cardId = medicalCardService.create(String.valueOf(patientDto.hashCode())).getId();
            patientDto.setCardId(cardId);
        }
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.updateUserToPatient(patientDto);
            User doctor = userDao.findById(patientDto.getDoctorId());
            userDao.updateDoctorPatientsNumber(doctor.getId(), doctor.getPatientsNumber() + 1);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }
}
