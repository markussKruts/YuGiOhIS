package jtt.test.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.DeckDAO;

import jtt.test.dto.Decks;
import jtt.test.dto.User;
import jtt.test.repositories.DeckRepository;

@Service
public class DeckDAOImpl implements DeckDAO {
	@Autowired
	DeckRepository repository;

	@Override
	public Decks insert(Decks value) {
		return repository.save(value);
	}

	@Override
	public Decks update(Decks value, int id) {
		Decks deckData = repository.findById(id).get();
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
	public Decks getByID(int id) {
		Decks DeckData = repository.findById(id).get();
		return DeckData;
	}

	@Override
	public int getID(Decks value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Decks> getAllData() {
		List<Decks> decks = (List<Decks>) repository.findAll();
		System.out.println("Service decks count: " + decks.size());
		return decks;
	}

	@Override
	public Decks getByName(String name) throws SQLException {
		return repository.findByName(name);
	}

	@Override
	public List<Decks> getByUser(User user) throws SQLException {
		return repository.findByUser(user);
	}
}
