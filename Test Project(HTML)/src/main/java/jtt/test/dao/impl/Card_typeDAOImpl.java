package jtt.test.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.Card_typeDAO;
import jtt.test.dto.Card_Type;


import jtt.test.repositories.Card_typeRepository;

@Service
public class Card_typeDAOImpl implements Card_typeDAO {
	@Autowired
	Card_typeRepository card_typeRepository;
	
	@Override
	public Card_Type insert(Card_Type value) {
		return card_typeRepository.save(value);
	}

	@Override
	public Card_Type update(Card_Type value, int id) {
		Card_Type card_typeData = card_typeRepository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("")) {
			card_typeData.setName(value.getName());
		}
		return card_typeRepository.save(value);
	}

	@Override
	public void delete(int id) {
		card_typeRepository.deleteById(id);
	}

	@Override
	public Card_Type getByID(int id) {
		Card_Type card_typeData = card_typeRepository.findById(id).get();
		return card_typeData;
	}

	@Override
	public int getID(Card_Type value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Card_Type> getAllData() {
		List<Card_Type> Card_Types = (List<Card_Type>) card_typeRepository.findAll();
		return Card_Types;
	}

	@Override
	public Card_Type getByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
