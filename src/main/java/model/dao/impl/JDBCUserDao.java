package model.dao.impl;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.JDBCDao;
import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.entity.Role;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao extends JDBCDao implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(JDBCUserDao.class);

    public JDBCUserDao(Connection connection) {
        super(connection);
    }

    private static final String INSERT_TEMPLATE =
            "INSERT INTO users (email, first_name, on_treatment, last_name, " +
                    "password, role, telephone_number, patients_number) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_DISCHARGE =
            "UPDATE users SET  on_treatment = ?, role = ?, doctor_id = ? WHERE id = ?";

    public static final String USERS_WHERE_ROLE = "SELECT * FROM users WHERE role = ?";
    public static final String USERS_WHERE_CARD_ID = "SELECT * FROM users WHERE card_id = ?";
    public static final String USERS_WHERE_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String USERS_WHERE_DOCTOR_ID = "SELECT * FROM users WHERE doctor_id = ?";
    public static final String USERS_WHERE_ID = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_ROLE = "UPDATE users SET role = ? WHERE id = ?";
    private static final String UPDATE_USER_TO_DOCTOR = "UPDATE users SET role = ?, qualification = ?, " +
            "patients_number = ? WHERE id = ?";
    private static final String UPDATE_DOCTORS_PATIENTS_NUMBER = "UPDATE users SET patients_number = ? WHERE id = ?";
    private static final String UPDATE_USER_TO_PATIENT = "UPDATE users SET role = ?, birth_date = ?, " +
            "on_treatment = ?, sex = ?, telephone_number = ?, doctor_id = ?, card_id = ? WHERE id = ?";
    public static final String FROM_USERS_BY_ASSIGNMENT_ID = "SELECT * FROM users " +
            " join assignment_nursehelper an on users.id = an.nurse_id where assignment_id = ?";
    public static final String FROM_PATIENTS_BY_NURSE_ID = "SELECT *FROM users WHERE users.card_id in (select distinct(a.card_id) from assignment_nursehelper an" +
            " join assignment a on a.id = an.assignment_id where an.nurse_id = ?)";

    @Override
    public User create(User user) {
        try (PreparedStatement ps = connection.prepareCall(INSERT_TEMPLATE)) {
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
        try (PreparedStatement ps = connection.prepareCall(USERS_WHERE_ID)) {
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (rs.next()) {
                return userMapper.extractFromResultSet(rs);
            }
            throw new EntityNotFoundException("There is no user with id [" + id + "]");
        } catch (Exception e) {
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
        try (PreparedStatement ps = connection.prepareCall(USERS_WHERE_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (rs.next()) {
                return userMapper.extractFromResultSet(rs);
            }
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        try (PreparedStatement ps = connection.prepareCall(USERS_WHERE_ROLE)) {
            List<User> users = new ArrayList<>();
            ps.setString(1, role.name());
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
            return users;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public List<User> getUsersByNurseId(long id) {
        try (PreparedStatement ps = connection.prepareCall(FROM_PATIENTS_BY_NURSE_ID)) {
            List<User> users = new ArrayList<>();
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
            return users;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public List<User> getUserByDoctorId(long id) {
        return getUsers(id, USERS_WHERE_DOCTOR_ID);
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
            ps.setString(1, Role.DOCTOR.name());
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
    public void updateUserToPatient(PatientDto patientDto) throws SQLException {
        try(PreparedStatement ps = connection.prepareCall(UPDATE_USER_TO_PATIENT)) {
            ps.setString( 1, Role.PATIENT.name());
            ps.setDate( 2, Date.valueOf(patientDto.getBirthDate()));
            ps.setBoolean(3, patientDto.getIsOnTreatment());
            ps.setString(4, patientDto.getSex().name());
            ps.setString(5, patientDto.getTelephoneNumber());
            ps.setLong( 6, patientDto.getDoctorId());
            ps.setLong( 7, patientDto.getCardId());
            ps.setLong( 8, patientDto.getId());
            User doctor = this.findById(patientDto.getDoctorId());
            this.connection.setAutoCommit(false);
            ps.executeUpdate();
            this.updateDoctorPatientsNumber(doctor.getId(), doctor.getPatientsNumber() + 1);
            this.connection.setAutoCommit(true);
        }catch (Exception e) {
            this.connection.rollback();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void updateDoctorPatientsNumber(Long id, int patientsNumber) {
        try (PreparedStatement ps = connection.prepareCall(UPDATE_DOCTORS_PATIENTS_NUMBER)) {
            ps.setInt( 1, patientsNumber);
            ps.setLong( 2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public List<User> getNurserByAssignmentID(long id) {
        return getUsers(id, FROM_USERS_BY_ASSIGNMENT_ID);
    }

    @Override
    public User getUserByMedicalCardId(long medicalCardId) {
        try (PreparedStatement ps = connection.prepareCall(USERS_WHERE_CARD_ID)) {
            ps.setLong(1, medicalCardId);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (rs.next()) {
                return userMapper.extractFromResultSet(rs);
            }
            throw new EntityNotFoundException("error.wrongCredential");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    private List<User> getUsers(long id, String fromUsersByAssignmentId) {
        try (PreparedStatement ps = connection.prepareCall(fromUsersByAssignmentId)) {
            List<User> users = new ArrayList<>();
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
            return users;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void dischargePatient(User user) throws SQLException {
        try(PreparedStatement ps = connection.prepareCall(UPDATE_USER_DISCHARGE)) {
            ps.setBoolean( 1, false);
            ps.setString( 2, Role.UNDEFINE.name());
            ps.setString(3, null);
            ps.setLong(4, user.getId());
            this.connection.setAutoCommit(false);
            User doctor = this.findById(user.getDoctorId());
            ps.executeUpdate();
            this.updateDoctorPatientsNumber(doctor.getId(), doctor.getPatientsNumber() - 1);
            this.connection.setAutoCommit(true);
        } catch (Exception e) {
            this.connection.rollback();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }
}
