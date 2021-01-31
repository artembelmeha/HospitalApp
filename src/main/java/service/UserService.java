package service;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dto.UserDto;
import model.entity.Role;
import model.entity.User;
import org.apache.log4j.Logger;
import utils.EncryptUtil;

import java.util.List;

import static model.entity.Role.UNDEFINE;


public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    public long signIn(String email, String password) {
        try(UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            User user =  userDao.findUserByEmail(email);
            if(EncryptUtil.encryptString(password).equals(user.getPassword())) {
                return user.getId();
            }
            throw new UnknownSqlException("error.wrongPassword");
        } catch (UnknownSqlException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public User getUserByEmail(String email) {
        try(UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.findUserByEmail(email);
        } catch (EntityNotFoundException |UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public List<User> getUsersByRole(Role role) {
        try(UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getUsersByRole(role);
        } catch (EntityNotFoundException |UnknownSqlException e) {
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
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()){
            return userDao.create(user);
        }
    }
    public List<User> getUsersByDoctorId(long id) {
        try(UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.getUserByDoctorId(id);
        } catch (EntityNotFoundException |UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }
    public User getUserById(long id) {
        try(UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.findById(id);
        } catch (EntityNotFoundException |UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }
}
