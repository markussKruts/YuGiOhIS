package jtt.test.dao;


import java.sql.SQLException;


import jtt.test.dto.Card_Type;

public interface Card_typeDAO extends GenericDAO<Card_Type> {
	Card_Type getByName(String name) throws SQLException;
}
