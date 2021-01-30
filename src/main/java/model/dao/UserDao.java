package model.dao;

import exception.ErrorMessageKeysContainedException;
import exception.UnknownSqlException;
import model.entity.Role;
import model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{

    User findUserByEmail(String email) throws UnknownSqlException, ErrorMessageKeysContainedException;

    List<User> getUsersByRole(Role role) throws UnknownSqlException, ErrorMessageKeysContainedException;

}
