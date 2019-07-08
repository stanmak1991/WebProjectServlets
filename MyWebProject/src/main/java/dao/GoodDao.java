package dao;

import model.Good;

public interface GoodDao extends GenericDao<Good> {

    boolean checkGood(String name);

    void updateGood(Long id, String name, Double price, String description);
}
