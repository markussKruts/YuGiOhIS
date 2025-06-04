package jtt.test.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.DeckDAO;
import jtt.test.dao.Deck_cardsDAO;
import jtt.test.dto.Card;
import jtt.test.dto.Deck;
import jtt.test.dto.Deck_Cards;
import jtt.test.repositories.DeckRepository;
import jtt.test.repositories.Deck_CardsRepository;

@Service
public class Deck_CardsDAOImpl implements Deck_cardsDAO {
	@Autowired
	Deck_CardsRepository repository;

	@Override
	public Deck_Cards insert(Deck_Cards value) {
		return repository.save(value);
	}

	@Override
	public Deck_Cards update(Deck_Cards value, int id) {
		Deck_Cards deckData = repository.findById(id).get();
		if(Objects.nonNull(value.getDeck()) && !value.getCard().equals("")) {
			deckData.setDeck(value.getDeck());
			deckData.setCard((Card) value.getCard());
			
		}
		return repository.save(deckData);
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);
		
	}

	@Override
	public Deck_Cards getByID(int id) {
		Deck_Cards DeckData = repository.findById(id).get();
		return DeckData;
	}

	@Override
	public int getID(Deck_Cards value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Deck_Cards> getAllData() {
		List<Deck_Cards> decks = (List<Deck_Cards>) repository.findAll();
		System.out.println("Service decks count: " + decks.size());
		return decks;
	}
}
