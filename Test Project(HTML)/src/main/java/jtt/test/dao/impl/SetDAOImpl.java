package jtt.test.dao.impl;


import java.sql.Date;

import java.sql.SQLException;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.SetDAO;

import jtt.test.dto.Set;
import jtt.test.dto.User;
import jtt.test.repositories.SetRepository;
@Service
public class SetDAOImpl implements SetDAO {
	@Autowired
	SetRepository setRepository;
	
	@Override
	public Set insert(Set value) {
		return setRepository.save(value);
	}

	@Override
	public Set update(Set value, int id) {
		Set setData = setRepository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("") && Objects.nonNull(value.getCode()) && !value.getCode().equals("")) {
			setData.setName(value.getName());
			setData.setCode(value.getCode());
		}
		return setRepository.save(value);
	}

	@Override
	public void delete(int id) {
		setRepository.deleteById(id);
		
	}

	@Override
	public Set getByID(int id) {
		Set setData = setRepository.findById(id).get();
		System.out.println("Fetched Set: " + setData.getName());
		return setData;
	}

	@Override
	public int getID(Set value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Set> getAllData() {
		List<Set> sets = (List<Set>) setRepository.findAll();
		return sets;
	}

	@Override
	public Set getByName(String name) throws SQLException {
		return setRepository.findByName(name);
	}

	@Override
	public Set getByCode(String code) throws SQLException {
		// TODO Auto-generated method stub
		return setRepository.findByCode(code);
	}

	@Override
	public List<Set> getByUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return setRepository.findByUser(user);
	}
	

}
