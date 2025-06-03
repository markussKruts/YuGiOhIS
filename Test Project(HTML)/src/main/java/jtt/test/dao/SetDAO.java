package jtt.test.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import jtt.test.dto.Card;
import jtt.test.dto.Set;
import jtt.test.dto.User;



public interface SetDAO extends GenericDAO<Set> {
	final String TABLE = "set";
	Set getByName(String name) throws SQLException;
	Set getByCode(String code) throws SQLException;
	List<Set> getByUser(User user) throws SQLException;
}
