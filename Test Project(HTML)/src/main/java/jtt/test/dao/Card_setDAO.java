package jtt.test.dao;

import java.sql.SQLException;

import jtt.test.dto.Card_set;



public interface Card_setDAO extends GenericDAO<Card_set> {
	Card_set getByName(String name) throws SQLException;
}
