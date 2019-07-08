package utils;

import model.Code;
import model.Good;
import model.Item;
import model.Role;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static final Logger LOGGER = Logger.getLogger(HibernateSessionFactoryUtil.class);
    private static SessionFactory sessionFactory = null;

    private HibernateSessionFactoryUtil() {
    }

    //before start SET GLOBAL time_zone = '+2:00'; in SQL Server
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(Good.class);
                configuration.addAnnotatedClass(Code.class);
                configuration.addAnnotatedClass(Item.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (HibernateException e) {
                LOGGER.error("Can't create session with DB", e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}