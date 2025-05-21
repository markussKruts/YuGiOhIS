package jtt.test.dao;

import java.sql.SQLException;

import jtt.test.dto.Rarities;



public interface RaritiesDAO extends GenericDAO<Rarities> {
	Rarities getByName(String name) throws SQLException;
}
