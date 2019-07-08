package dao;

import model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role getRoleByName(String roleName);
}
