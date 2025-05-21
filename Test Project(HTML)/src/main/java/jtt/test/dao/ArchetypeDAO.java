package jtt.test.dao;



import jtt.test.dto.Archetype;

public interface ArchetypeDAO extends GenericDAO<Archetype> {
	Archetype getByName(String name);
}
