package jtt.test.dao.impl;


import java.sql.SQLException;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.RaritiesDAO;

import jtt.test.dto.Rarities;

import jtt.test.repositories.RaritiesRepository;
@Service
public class RaritiesDAOImpl implements RaritiesDAO {
	@Autowired
	RaritiesRepository raritiesRepository;
	
	@Override
	public Rarities insert(Rarities value) {
		return raritiesRepository.save(value);
	}

	@Override
	public Rarities update(Rarities value, int id) {
		Rarities rarityData = raritiesRepository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("")) {
			rarityData.setName(value.getName());
		}
		return raritiesRepository.save(value);
	}

	@Override
	public void delete(int id) {
		raritiesRepository.deleteById(id);
		
	}

	@Override
	public Rarities getByID(int id) {
		Rarities rarityData = raritiesRepository.findById(id).get();
		return rarityData;
	}

	@Override
	public int getID(Rarities value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Rarities> getAllData() {
		List<Rarities> rarities = (List<Rarities>) raritiesRepository.findAll();
		return rarities;
	}

	@Override
	public Rarities getByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return raritiesRepository.findByName(name);
	}

	

}
