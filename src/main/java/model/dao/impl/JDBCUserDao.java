package model.dao.impl;

import exception.UnknownSqlException;
import model.dao.JDBCDao;
import model.dao.UserDao;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.entity.Role;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static model.dao.mapper.UserMapper.extractUser;
import static model.dao.mapper.UserMapper.extractUsers;
import static model.entity.Role.*;

public class JDBCUserDao extends JDBCDao implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(JDBCUserDao.class);


    private static final String CREATE_USER = "INSERT INTO users (email, first_name, on_treatment, last_name, "
          + "password, role, telephone_number, patients_number) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_DISCHARGE = "UPDATE users SET  on_treatment = ?, role = ?, doctor_id = ? WHERE id = ?";
    private static final String GET_USERS_BY_ROLE = "SELECT * FROM users WHERE role = ?";
    private static final String GET_USER_BY_CARD_ID = "SELECT * FROM users WHERE card_id = ?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String GET_USERS_BY_DOCTOR_ID = "SELECT * FROM users WHERE doctor_id = ?";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_ROLE = "UPDATE users SET role = ? WHERE id = ?";
    private static final String UPDATE_USER_TO_DOCTOR =
          "UPDATE users SET role = ?, qualification = ?, " + "patients_number = ? WHERE id = ?";
    private static final String UPDATE_USER_TO_PATIENT = "UPDATE users SET role = ?, birth_date = ?, " +
            "on_treatment = ?, sex = ?, telephone_number = ?, doctor_id = ?, card_id = ? WHERE id = ?";
    private static final String GET_NURSES_BY_ASSIGMENT_ID = "SELECT * FROM users " +
            " join assignment_nursehelper an on users.id = an.nurse_id where assignment_id = ?";
    private static final String GET_PATIENTS_BY_NURSE_ID = "SELECT *FROM users WHERE users.card_id in (select distinct(a.card_id) from assignment_nursehelper an" +
            " join assignment a on a.id = an.assignment_id where an.nurse_id = ?)";
    private static final String INCREMENT_PATIENTS_NUMBER_BY_DOCTOR_ID = "UPDATE users SET patients_number = patients_number + 1 WHERE id = ?";
    private static final String DECREMENT_PATIENTS_NUMBER_BY_DOCTOR_ID = "UPDATE users SET patients_number = patients_number - 1 WHERE id = ?";

    public JDBCUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        try (PreparedStatement ps = connection.prepareCall(CREATE_USER)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setBoolean(3, user.isOnTreatment());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole().name());
            ps.setString(7, user.getTelephoneNumber());
            ps.setInt(8, user.getPatientsNumber());
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
        User savedUser = this.findUserByEmail(user.getEmail());
        LOGGER.debug("User with id [ " + savedUser.getId() + " ] successfully saved.");
        return savedUser;
    }

    @Override
    public User findById(long id) {
        try (PreparedStatement ps = connection.prepareCall(GET_USER_BY_ID)) {
            ps.setLong(1, id);
            return extractUser(ps.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        //for future
    }

    @Override
    public void delete(long id) {
        //for future
    }
    
    @Override
    public User findUserByEmail(String email) {
        try (PreparedStatement ps = connection.prepareCall(GET_USER_BY_EMAIL)) {
            ps.setString(1, email);
            return extractUser(ps.executeQuery());
        } catch (SQLException e) {
            return proccessException(e);
        }
    }

    private User proccessException(SQLException e) {
        LOGGER.error(e.getMessage());
        throw new UnknownSqlException(e.getMessage());
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        try (PreparedStatement ps = connection.prepareCall(GET_USERS_BY_ROLE)) {
            ps.setString(1, role.name());
            return extractUsers(ps.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public List<User> getUsersByNurseId(long id) {
        try (PreparedStatement ps = connection.prepareCall(GET_PATIENTS_BY_NURSE_ID)) {
            ps.setLong(1, id);
            return extractUsers(ps.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void updateUserRole(long id, Role role) {
        try (PreparedStatement ps = connection.prepareCall(UPDATE_USER_ROLE)) {
            ps.setString(1, role.name());
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void updateUserToDoctor(DoctorDto doctorDto) {
        try (PreparedStatement ps = connection.prepareCall(UPDATE_USER_TO_DOCTOR)) {
            ps.setString(1, DOCTOR.name());
            ps.setString(2, doctorDto.getQualification().name());
            ps.setInt(3, doctorDto.getPatientsNumber());
            ps.setLong(4, doctorDto.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void updateDoctorPatientsNumber(long doctorId, boolean increment) {
        try (PreparedStatement ps = connection.prepareCall(
              increment ? INCREMENT_PATIENTS_NUMBER_BY_DOCTOR_ID : DECREMENT_PATIENTS_NUMBER_BY_DOCTOR_ID)) {
            ps.setLong(1, doctorId);
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public List<User> getUserByDoctorId(long id) {
        return getUsers(id, GET_USERS_BY_DOCTOR_ID);
    }

    @Override
    public List<User> getNurserByAssignmentId(long id) {
        return getUsers(id, GET_NURSES_BY_ASSIGMENT_ID);
    }

    private List<User> getUsers(long id, String query) {
        try (PreparedStatement ps = connection.prepareCall(query)) {
            ps.setLong(1, id);
            return extractUsers(ps.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public User getUserByMedicalCardId(long medicalCardId) {
        try (PreparedStatement ps = connection.prepareCall(GET_USER_BY_CARD_ID)) {
            ps.setLong(1, medicalCardId);
            return extractUser(ps.executeQuery());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void updateUserToPatient(PatientDto patientDto) {
        try(PreparedStatement ps = connection.prepareCall(UPDATE_USER_TO_PATIENT)) {
            ps.setString( 1, PATIENT.name());
            ps.setDate( 2, Date.valueOf(patientDto.getBirthDate()));
            ps.setBoolean(3, patientDto.getIsOnTreatment());
            ps.setString(4, patientDto.getSex().name());
            ps.setString(5, patientDto.getTelephoneNumber());
            ps.setLong( 6, patientDto.getDoctorId());
            ps.setLong( 7, patientDto.getCardId());
            ps.setLong( 8, patientDto.getId());

            connection.setAutoCommit(false);
            ps.executeUpdate();
            updateDoctorPatientsNumber(patientDto.getDoctorId(), true);
            connection.setAutoCommit(true);
        } catch (Exception e) {
            LOGGER.error("Error during updateUserToPatient. Trying rollback.", e);
            try {
                connection.rollback();
            } catch (SQLException exc) {
                LOGGER.error("Error during rollback.", exc);
                throw new UnknownSqlException(exc.getMessage());
            }
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void dischargePatient(User user)  {
        try(PreparedStatement ps = connection.prepareCall(UPDATE_USER_DISCHARGE)) {
            connection.setAutoCommit(false);

            ps.setBoolean( 1, false);
            ps.setString( 2, UNDEFINE.name());
            ps.setString(3, null);
            ps.setLong(4, user.getId());
            ps.executeUpdate();
            updateDoctorPatientsNumber(user.getDoctorId(), false);

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error("Error during dischargePatient. Trying rollback.", e);
            try {
                connection.rollback();
            } catch (SQLException exc) {
                LOGGER.error("Error during rollback.", exc);
                throw new UnknownSqlException(exc.getMessage());
            }
            throw new UnknownSqlException(e.getMessage());
        }
    }
}
