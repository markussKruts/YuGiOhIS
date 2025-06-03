package jtt.test.dao;

import java.sql.Date;
import java.sql.SQLException;

import jtt.test.dto.Set;



public interface SetDAO extends GenericDAO<Set> {
	final String TABLE = "set";
	Set getByName(String name) throws SQLException;
	Set getByCode(String code) throws SQLException;
}
