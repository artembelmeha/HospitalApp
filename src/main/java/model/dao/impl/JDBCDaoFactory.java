package model.dao.impl;

import exception.UnknownSqlException;
import model.dao.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(JDBCDaoFactory.class);

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();


    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public MedicalCardDao createMedicalCardDao() {
        return new JDBCMedicalCardDao(getConnection());
    }

    @Override
    public AssignmentDao getAssignmentDao() {
        return new JDBCAssignmentDao(getConnection());
    }

    @Override
    public AssignmentNursehelperDao createAssignmentNursehelperDao() {
        return new JDBCAssignmentNursehelperDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Sorry, something went wrong!", e);
            throw new UnknownSqlException(e.getMessage());
        }
    }
}
