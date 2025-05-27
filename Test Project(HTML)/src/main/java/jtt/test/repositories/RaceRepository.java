package jtt.test.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.FrameType;
import jtt.test.dto.Race;

public interface RaceRepository extends CrudRepository<Race, Integer>{
	@Query("SELECT u FROM Race u WHERE u.name = :name")
	Race findByName(@Param("name") String name);
}
