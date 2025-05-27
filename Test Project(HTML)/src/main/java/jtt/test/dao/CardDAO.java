package jtt.test.dao;

import java.sql.SQLException;
import java.util.List;

import jtt.test.dto.Card;

public interface CardDAO extends GenericDAO<Card>{
	Card getByName(String name) throws SQLException;
	List<Card> getByType(String type) throws SQLException;
}
