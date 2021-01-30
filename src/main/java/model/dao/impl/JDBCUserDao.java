package model.dao.impl;

import exception.EntityNotFoundException;
import exception.ErrorMessageKeysContainedException;
import exception.UnknownSqlException;
import model.dao.mapper.UserMapper;
import model.dto.UserDto;
import model.entity.Role;
import model.entity.User;
import model.dao.UserDao;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static model.entity.Role.UNDEFINE;

public class JDBCUserDao implements UserDao {

    private static final String INSERT_TEMPLATE =
            "INSERT INTO users (email, first_name, on_treatment, last_name, " +
                    "password, role, telephone_number, patients_number) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final Logger LOGGER = Logger.getLogger(JDBCUserDao.class);

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User user) {
        try(PreparedStatement ps = connection.prepareCall(INSERT_TEMPLATE)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setBoolean(3, user.isOnTreatment());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole().name());
            ps.setString(7, user.getTelephoneNumber());
            ps.setInt(8, user.getPatientsNumber());
            ps.execute();
        }  catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
        User savedUser = this.findUserByEmail(user.getEmail());
        LOGGER.debug("User with id [ "+savedUser.getId()+" ] successfully saved.");
        return savedUser;
    }


    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM users WHERE email = ?")){
            ps.setString( 1, email);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            if(rs.next()) {
                return userMapper.extractFromResultSet(rs);
            }
            throw new EntityNotFoundException("error.wrongCredential");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM users WHERE role = ?")) {
            List<User> users = new ArrayList<>();
            ps.setString( 1, role.name());
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                 users.add(userMapper.extractFromResultSet(rs));
            }
            return users;
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }
}
