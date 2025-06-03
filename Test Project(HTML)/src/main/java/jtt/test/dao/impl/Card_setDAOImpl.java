package jtt.test.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.Card_setDAO;
import jtt.test.dto.Card;
import jtt.test.dto.Card_set;
import jtt.test.dto.Set;
import jtt.test.repositories.Card_setRepository;
import jtt.test.repositories.SetRepository;

@Service
public class Card_setDAOImpl implements Card_setDAO{
	@Autowired
	Card_setRepository repository;

	@Override
	public Card_set insert(Card_set value) {
		return repository.save(value);
	}

	@Override
	public Card_set update(Card_set value, int id) {
		Card_set cardData = repository.findById(id).get();
		if(Objects.nonNull(value.getCard_id())) {
			cardData.setCard_id(value.getCard_id());
			cardData.setRarity_id(value.getRarity_id());
			cardData.setSet_id(value.getSet_id());
		}
		return repository.save(cardData);
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);
		
	}

	@Override
	public Card_set getByID(int id) {
		Card_set CardData = repository.findById(id).get();
		return CardData;
	}

	@Override
	public int getID(Card_set value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Card_set> getAllData() {
		List<Card_set> Cards = (List<Card_set>) repository.findAll();
		System.out.println("Service cards count: " + Cards.size());
		return Cards;
	}
	
	

}
