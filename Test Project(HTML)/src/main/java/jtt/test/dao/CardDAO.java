package jtt.test.dao;

import java.sql.SQLException;
import java.util.List;

import jtt.test.dto.Card;
import jtt.test.dto.User;

public interface CardDAO extends GenericDAO<Card>{
	Card getByName(String name) throws SQLException;
	List<Card> getByType(String type) throws SQLException;
	List<Card> getByUser(User user) throws SQLException;
}
