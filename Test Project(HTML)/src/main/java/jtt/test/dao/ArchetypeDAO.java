package jtt.test.dao;

import java.sql.SQLException;

import jtt.test.dto.Archetype;



public interface ArchetypeDAO extends GenericDAO<Archetype> {
	Archetype getByName(String name) throws SQLException;
}
