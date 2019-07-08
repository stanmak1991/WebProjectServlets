package dao.impl;

import dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @Override
    public void add(T object) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        }
    }

    @Override
    public T getById(Class<T> entityClass, Long id) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            return session.get(entityClass, id);
        }
    }

    @Override
    public List<T> getAll(Class<T> entityClass) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createCriteria(entityClass).list();
    }

    @Override
    public void delete(Class<T> entityClass, Long id) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            T object = session.get(entityClass, id);
            Transaction transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        }
    }
}
