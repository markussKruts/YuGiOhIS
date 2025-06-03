package jtt.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Card;
import jtt.test.dto.Deck_Cards;
import jtt.test.dto.Decks;
import jtt.test.dto.User;

public interface Deck_CardsRepository extends CrudRepository<Deck_Cards, Integer> {
	@Query("SELECT u FROM Deck_Cards u WHERE u.name = :name")
	Deck_Cards findByName(@Param("name") String name);
	
	@Query("SELECT v FROM Decks v WHERE v.card = :card")
	List<Decks> findByUser(@Param("user") Card Card);
}
