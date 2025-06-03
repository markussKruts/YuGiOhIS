package jtt.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Card;
import jtt.test.dto.Set;
import jtt.test.dto.User;

public interface SetRepository extends CrudRepository<Set, Integer>{
	@Query("SELECT c FROM Set c WHERE c.name = :name")
	Set findByName(@Param("name") String name);
	
	@Query("SELECT t FROM Set t WHERE t.code = :code")
	Set findByCode(@Param("code") String type);
}
