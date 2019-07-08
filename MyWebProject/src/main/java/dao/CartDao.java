package dao;

import model.Item;

import java.util.List;

public interface CartDao extends GenericDao<Item> {
    void update(Item item);

    List<Item> getAllByUserAndGood(Long userId, Long goodId);

    List<Item> getAllByUser(Long userId);

    List<Item> getAllByNotPayed(Long userId);

    void confirmUserPayment(Long userId);

    void deleteByUser(Long userId);
}
