package jtt.test.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Card_Type;
import jtt.test.dto.User;


public interface Card_typeRepository extends CrudRepository<Card_Type, Integer>{
	@Query("SELECT u FROM Card_Type u WHERE u.name = :name")
	Card_Type findByName(@Param("name") String name);
}
