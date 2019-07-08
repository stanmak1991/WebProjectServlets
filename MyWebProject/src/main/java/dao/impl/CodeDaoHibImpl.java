package dao.impl;

import dao.CodeDao;
import model.Code;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CodeDaoHibImpl extends GenericDaoImpl<Code> implements CodeDao {

    @Override
    public boolean check(Code code) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Query query = session.createQuery("from Code where value = :value");
            query.setParameter("value", code.getValue());
            List<Code> codeList = query.list();
            if (codeList.size() == 0) {
                return false;
            } else if (codeList.get(0).getUser().equals(code.getUser())) {
                code.setId(codeList.get(0).getId());
                return true;
            } else {
                return false;
            }
        }
    }
}
