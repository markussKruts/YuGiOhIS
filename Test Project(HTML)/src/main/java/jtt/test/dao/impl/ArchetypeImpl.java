package jtt.test.dao.impl;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.ArchetypeDAO;
import jtt.test.dto.Archetype;

import jtt.test.repositories.ArchetypeRepository;

@Service
public class ArchetypeImpl implements ArchetypeDAO {
	@Autowired
	ArchetypeRepository archetypeRepository;

	@Override
	public Archetype insert(Archetype value) {
		return archetypeRepository.save(value);
	}

	@Override
	public Archetype update(Archetype value, int id) {
		Archetype archetypeData = archetypeRepository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("")) {
			archetypeData.setName(value.getName());
		}
		return archetypeRepository.save(value);
	}

	@Override
	public void delete(int id) {
		archetypeRepository.deleteById(id);
		
	}

	@Override
	public Archetype getByID(int id) {
		Archetype archetypeData = archetypeRepository.findById(id).get();
		return archetypeData;
	}

	@Override
	public int getID(Archetype value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Archetype> getAllData() {
		List<Archetype> archetypes = (List<Archetype>) archetypeRepository.findAll();
		return archetypes;
	}

	@Override
	public Archetype getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
