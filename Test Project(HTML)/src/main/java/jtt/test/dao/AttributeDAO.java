package jtt.test.dao;

import java.sql.SQLException;

import jtt.test.dto.Attribute;


public interface AttributeDAO extends GenericDAO<Attribute> {
	Attribute getByName(String name) throws SQLException;
}
