package dao;

import model.Code;

public interface CodeDao extends GenericDao<Code> {
    boolean check(Code code);
}
