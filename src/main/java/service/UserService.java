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
        User user = getUser(userDto);
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.create(user);
        }
    }

    private User getUser(UserDto userDto) {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setRole(UNDEFINE);
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setOnTreatment(false);
        user.setPatientsNumber(0);
        return user;
    }

    public List<User> getUsersByDoctorId(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getUserByDoctorId(id);
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
            LOGGER.info("User #[" + id + "] was successfully assigned as nurse");
        }

    }

    public void assignAsDoctor(DoctorDto doctorDto) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.updateUserToDoctor(doctorDto);
        }
    }

    public void assignAsPatient(PatientDto patientDto) {
        if (patientDto.getCardId() == 0) {
            MedicalCard medicalCard = medicalCardService.create(String.valueOf(patientDto.getId()));
            patientDto.setCardId(medicalCard.getId());
        }

        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.updateUserToPatient(patientDto);
            User doctor = userDao.findById(patientDto.getDoctorId());
            userDao.updateDoctorPatientsNumber(doctor.getId(), doctor.getPatientsNumber() + 1);
            LOGGER.info("User #[" + patientDto.getId() + "] was successfully assigned as patient");
        }
    }

    public List<UserDto> getNursesByAssignmentID(long id) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getNurserByAssignmentID(id).stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList());
        }
    }

    public void dischargePatientByMedicalCardId(long medicalCardId) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
             User user = userDao.getUserByMedicalCardId(medicalCardId);
             userDao.dischargePatient(user);

             User doctor = userDao.findById(user.getDoctorId());
             userDao.updateDoctorPatientsNumber(doctor.getId(), doctor.getPatientsNumber() - 1);
        }
    }
}
