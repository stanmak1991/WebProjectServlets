package dao;

import java.util.List;

public interface GenericDao<T> {
    void add(T object);

    T getById(Class<T> entityClass, Long id);

    List<T> getAll(Class<T> entityClass);

    void delete(Class<T> entityClass, Long id);
}
