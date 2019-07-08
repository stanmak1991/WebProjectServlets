package dao;

import model.Role;
import model.User;

public interface UserDao extends GenericDao<User> {

    boolean check(User user);

    User getByLogin(String login);

    void update(Long id, String password, String email, Role role);
}
