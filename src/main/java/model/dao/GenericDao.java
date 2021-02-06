package model.dao;

import java.sql.SQLException;

public interface GenericDao<T> extends AutoCloseable {
    T create (T entity);
    T findById(long id);
    void update(T entity) throws SQLException;
    void delete(long id);
    void close();
}

