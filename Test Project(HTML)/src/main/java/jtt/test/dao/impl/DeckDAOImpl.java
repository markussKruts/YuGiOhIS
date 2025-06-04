package jtt.test.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.DeckDAO;

import jtt.test.dto.Deck;
import jtt.test.dto.Set;
import jtt.test.dto.User;
import jtt.test.repositories.DeckRepository;

@Service
public class DeckDAOImpl implements DeckDAO {
	@Autowired
	DeckRepository repository;

	@Override
	public Deck insert(Deck value) {
		return repository.save(value);
	}

	@Override
	public Deck update(Deck value, int id) {
		Deck deckData = repository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("")) {
			deckData.setName(value.getName());
			deckData.setUser(value.getUser());
			
		}
		return repository.save(deckData);
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);
		
	}

	@Override
	public Deck getByID(int id) {
		Deck DeckData = repository.findById(id).get();
		return DeckData;
	}

	@Override
	public int getID(Deck value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Deck> getAllData() {
		List<Deck> decks = (List<Deck>) repository.findAll();
		System.out.println("Service decks count: " + decks.size());
		return decks;
	}

	@Override
	public List<Deck> getByUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return repository.findByUser(user);
	}


}
