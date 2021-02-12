package service;

import exception.UnknownSqlException;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.dto.UserDto;
import model.entity.MedicalCard;
import model.entity.Role;
import model.entity.User;
import org.apache.log4j.Logger;
import utils.EncryptUtil;

import java.util.List;
import java.util.stream.Collectors;

import static model.entity.Role.NURSE;



public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private static final String USER_NO = "User #[";
    private static final String WAS_ASSIGNED_AS = "] was successfully assigned as ";

    private static MedicalCardService medicalCardService = ServiceFactory.getInstance().getCardService();

    public long signIn(String email, String password) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            User user = userDao.findUserByEmail(email);
            if (EncryptUtil.encryptString(password).equals(user.getPassword())) {
                return user.getId();
            }
            LOGGER.info("Wrong credential for email [" + email + "]" );
            throw new UnknownSqlException("Wrong credential!!!");
        }
    }

    public User getUserByEmail(String email) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.findUserByEmail(email);
        }
    }

    public List<User> getUsersByRole(Role role) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getUsersByRole(role);
        }
    }

    public User create(UserDto userDto) {
        User user = userDto.getUser();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.create(user);
        }
    }


    public List<User> getUsersByDoctorId(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getUserByDoctorId(id);
        }
    }
    public List<User> getUsersByAssignedNurse(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getUsersByNurseId(id);
        }
    }

    public User getUserById(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.findById(id);
        }
    }

    public void assignAsNurse(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.updateUserRole(id, NURSE);
            LOGGER.info(USER_NO + id + WAS_ASSIGNED_AS + NURSE);
        }
    }

    public void assignAsDoctor(DoctorDto doctorDto) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.updateUserToDoctor(doctorDto);
            LOGGER.info(USER_NO + doctorDto.getId() + WAS_ASSIGNED_AS + doctorDto.getRole());
        }
    }

    public void assignAsPatient(PatientDto patientDto) {
        if (patientDto.getCardId() == 0) {
            MedicalCard medicalCard = medicalCardService.create(String.valueOf(patientDto.getId()));
            patientDto.setCardId(medicalCard.getId());
            LOGGER.info("Medical card id# [" + medicalCard.getId() + "] was created");
        }

        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.updateUserToPatient(patientDto);
            LOGGER.info(USER_NO + patientDto.getId() + WAS_ASSIGNED_AS + patientDto.getRole());
        }
    }

    public List<UserDto> getNursesByAssignmentID(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getNurserByAssignmentId(id).stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList());
        }
    }

    public void dischargePatientByMedicalCardId(long medicalCardId) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
             User user = userDao.getUserByMedicalCardId(medicalCardId);
             userDao.dischargePatient(user);
             LOGGER.info("Patient id# [" + user.getId() +"] was successfully discharged ");
        }
    }
}
