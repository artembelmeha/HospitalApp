package model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {


    public static final String MYSQL_LOCALHOST_3306_HOSPITAL = "jdbc:mysql://localhost:3306/hospital";
    public static final String COM_MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "12345678";
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
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}