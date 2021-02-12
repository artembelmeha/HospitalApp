package model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {

    public static final String MYSQL_LOCALHOST_3306_HOSPITAL = "jdbc:mysql://localhost:3306/hospital";
    public static final String COM_MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "12345678";
    public static final int MAX_OPEN_STATEMENTS = 100;
    public static final int MAX_IDLE = 10;
    public static final int MIN_IDLE = 5;

    private static volatile DataSource dataSource;

    public static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(COM_MYSQL_CJ_JDBC_DRIVER);
                    ds.setUrl(MYSQL_LOCALHOST_3306_HOSPITAL);
                    ds.setUsername(USER_NAME);
                    ds.setPassword(PASSWORD);
                    ds.setMinIdle(MIN_IDLE);
                    ds.setMaxIdle(MAX_IDLE);
                    ds.setMaxOpenPreparedStatements(MAX_OPEN_STATEMENTS);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}