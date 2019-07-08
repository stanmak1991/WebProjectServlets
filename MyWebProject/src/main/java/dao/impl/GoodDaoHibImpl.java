package dao.impl;

import dao.GoodDao;
import model.Good;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class GoodDaoHibImpl extends GenericDaoImpl<Good> implements GoodDao {
    @Override
    public boolean checkGood(String name) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Query query = session.createQuery("from Good where name = :name");
            query.setParameter("name", name);
            List<Good> goodsList = query.list();
            if (goodsList.size() == 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void updateGood(Long id, String name, Double price, String description) {
        Good good = getById(Good.class, id);
        if (!name.equals("")) {
            good.setName(name);
        }
        if (!description.equals("")) {
            good.setDescription(description);
        }
        if (price != null && price > 0) {
            good.setPrice(price);
        }
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(good);
            transaction.commit();
        }
    }
}
