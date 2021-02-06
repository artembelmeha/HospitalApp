package model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    T create (T entity);
    T findById(long id);
    List<T> findAll();
    void update(T entity) throws SQLException;
    void delete(long id);
    void close();
}

