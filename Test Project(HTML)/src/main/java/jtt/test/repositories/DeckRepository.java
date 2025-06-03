package jtt.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Card;
import jtt.test.dto.Decks;
import jtt.test.dto.User;


public interface DeckRepository extends CrudRepository<Decks, Integer> {
	@Query("SELECT u FROM Decks u WHERE u.name = :name")
	Decks findByName(@Param("name") String name);
	
	@Query("SELECT v FROM Decks v WHERE v.user = :user")
	List<Decks> findByUser(@Param("user") User user);
}
