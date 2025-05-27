package jtt.test.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Archetype;
import jtt.test.dto.Attribute;


public interface AttributeRepository extends CrudRepository<Attribute, Integer>{
	@Query("SELECT u FROM Attribute u WHERE u.name = :name")
	Attribute findByName(@Param("name") String name);
}
