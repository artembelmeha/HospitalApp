package model.dao;

public interface GenericDao<T> extends AutoCloseable {
    T create (T entity);
    T findById(long id);
    void update(T entity);
    void delete(long id);
    void close();
}

