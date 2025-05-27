package jtt.test.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Archetype;
import jtt.test.dto.User;

public interface ArchetypeRepository extends CrudRepository<Archetype, Integer>{
	@Query("SELECT u FROM Archetype u WHERE u.name = :name")
	Archetype findByName(@Param("name") String name);
}
