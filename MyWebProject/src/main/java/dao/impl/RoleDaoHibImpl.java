package dao.impl;

import dao.RoleDao;
import model.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class RoleDaoHibImpl extends GenericDaoImpl<Role> implements RoleDao {

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Query query = session.createQuery("from Role where role = :roleName");
            query.setParameter("roleName", roleName);
            List<Role> rolesList = query.list();
            if (rolesList.size() == 0) {
                return null;
            } else {
                return rolesList.get(0);
            }
        }
    }
}
