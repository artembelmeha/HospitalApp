package model.dao;

import exception.UnknownSqlException;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class JDBCDao {
    protected Connection connection;

    public JDBCDao(Connection connection) {
        this.connection = connection;
    }
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
