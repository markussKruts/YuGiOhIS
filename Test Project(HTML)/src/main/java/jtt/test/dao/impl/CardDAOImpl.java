package jtt.test.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.CardDAO;
import jtt.test.dto.Card;
import jtt.test.dto.User;
import jtt.test.repositories.CardRepository;

@Service
public class CardDAOImpl implements CardDAO{
	@Autowired
	CardRepository repository;

	@Override
	public Card insert(Card value) {
		return repository.save(value);
	}

	@Override
	public Card update(Card value, int id) {
		Card cardData = repository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("")) {
			cardData.setName(value.getName());
			cardData.setCard_type(value.getCard_type());
			cardData.setDescription(value.getDescription());
			cardData.setArchetype(value.getArchetype());
			cardData.setAttribute(value.getAttribute());
			cardData.setAttack(value.getAttack());
			cardData.setDefense(value.getDefense());
			cardData.setLevel(value.getLevel());
			cardData.setRace(value.getRace());
			cardData.setFrame_type(value.getFrame_type());
			cardData.setImage(value.getImage());
		}
		return repository.save(cardData);
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);
	}

	@Override
	public Card getByID(int id) {
		Card CardData = repository.findById(id).get();
		return CardData;
	}

	@Override
	public int getID(Card value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Card> getAllData() {
		List<Card> Cards = (List<Card>) repository.findAll();
		System.out.println("Service cards count: " + Cards.size());
		return Cards;
	}

	@Override
	public Card getByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Card> getByType(String type) throws SQLException {
		return repository.findByType(type);
	}

	@Override
	public List<Card> getByUser(User user) throws SQLException {
		return repository.findByUser(user);
	}

}
