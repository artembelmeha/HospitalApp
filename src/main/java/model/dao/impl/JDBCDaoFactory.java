package model.dao.impl;

import model.dao.DaoFactory;
import model.dao.UserDao;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger logger = Logger.getLogger(JDBCDaoFactory.class);

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();


    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Sorry, something went wrong!", e);
            throw new RuntimeException(e);
        }
    }
}
