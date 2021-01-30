package service;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.DaoFactory;
import model.dto.UserDto;
import model.entity.Role;
import model.entity.User;
import org.apache.log4j.Logger;
import utils.EncryptUtil;

import java.util.List;

import static model.entity.Role.UNDEFINE;


public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    DaoFactory daoFactory = DaoFactory.getInstance();


    public long signIn(String email, String password) {
        try {
            User user =  daoFactory.createUserDao().findUserByEmail(email);
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
        try {
            return daoFactory.createUserDao().findUserByEmail(email);
        } catch (EntityNotFoundException |UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public List<User> getUsersByRole(Role role) {
        try {
            return daoFactory.createUserDao().getUsersByRole(role);
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
        return daoFactory.createUserDao().create(user);
    }


}
