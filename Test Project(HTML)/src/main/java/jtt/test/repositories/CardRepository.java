package jtt.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Card;
import jtt.test.dto.User;

public interface CardRepository extends CrudRepository<Card, Integer>{
	@Query("SELECT c FROM Card c WHERE c.name = :name")
	Card findByName(@Param("name") String name);
	
	@Query("SELECT t FROM Card t WHERE t.card_type = :card_type")
	List<Card> findByType(@Param("type") String type);
	
	@Query("SELECT v FROM Card v WHERE v.user = :user")
	List<Card> findByUser(@Param("user") User user);
}
