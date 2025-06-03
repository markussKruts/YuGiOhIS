package jtt.test.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Rarities;

public interface RaritiesRepository extends CrudRepository<Rarities, Integer>{
	@Query("SELECT u FROM Rarities u WHERE u.name = :name")
	Rarities findByName(@Param("name") String name);
}
