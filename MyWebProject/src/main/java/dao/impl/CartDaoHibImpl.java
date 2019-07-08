package dao.impl;

import dao.CartDao;
import model.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CartDaoHibImpl extends GenericDaoImpl<Item> implements CartDao {

    @Override
    public void update(Item item) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        }
    }

    @Override
    public List<Item> getAllByUserAndGood(Long userId, Long goodId) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Query query = session.createQuery("from Item where user_id = :userId AND good_id = :goodId AND payed = false");
            query.setParameter("userId", userId);
            query.setParameter("goodId", goodId);
            List<Item> itemsList = query.list();
            return itemsList;
        }
    }

    @Override
    public List<Item> getAllByUser(Long userId) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Query query = session.createQuery("from Item where user_id = :id");
            query.setParameter("id", userId);
            List<Item> itemsList = query.list();
            return itemsList;
        }
    }

    @Override
    public List<Item> getAllByNotPayed(Long userId) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Query query = session.createQuery("from Item where user_id = :id AND payed = false");
            query.setParameter("id", userId);
            List<Item> itemsList = query.list();
            return itemsList;
        }
    }

    @Override
    public void confirmUserPayment(Long userId) {
        List<Item> allUserItems = getAllByNotPayed(userId);
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            for (Item item : allUserItems) {
                Transaction transaction = session.beginTransaction();
                item.setPayed(true);
                session.update(item);
                transaction.commit();
            }
        }
    }

    @Override
    public void deleteByUser(Long userId) {
        List<Item> items = getAllByUser(userId);
        for (Item item : items) {
            delete(Item.class, item.getId());
        }
    }
}
