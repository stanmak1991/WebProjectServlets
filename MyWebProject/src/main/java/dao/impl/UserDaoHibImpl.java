package dao.impl;

import dao.UserDao;
import model.Role;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HashUtil;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDaoHibImpl extends GenericDaoImpl<User> implements UserDao {
    @Override
    public boolean check(User user) {
        User userFromBase = getByLogin(user.getLogin());
        if (userFromBase != null) {
            if (user.getLogin().equals(userFromBase.getLogin())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getByLogin(String login) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Query query = session.createQuery("from User where login = :login");
            query.setParameter("login", login);
            List<User> usersList = query.list();
            if (usersList.size() == 0) {
                return null;
            } else {
                return usersList.get(0);
            }
        }
    }

    @Override
    public void update(Long id, String password, String email, Role role) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            User user = session.get(User.class, id);
            if (!password.equals("")) {
                String salt = HashUtil.getRandomSalt();
                password = HashUtil.getSHA512SecurePassword(password, salt);
                user.setPassword(password);
                user.setSalt(salt);
            }
            if (!email.equals("")) {
                user.setEmail(email);
            }
            if (!user.getRole().equals(role)) {
                user.setRole(role);
            }
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }
}
