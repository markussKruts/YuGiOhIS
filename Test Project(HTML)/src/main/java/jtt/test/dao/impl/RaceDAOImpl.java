package jtt.test.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.RaceDAO;
import jtt.test.dto.Race;
import jtt.test.repositories.RaceRepository;

@Service
public class RaceDAOImpl implements RaceDAO{
	@Autowired
	RaceRepository raceRepository;

	@Override
	public Race insert(Race value) {
		return raceRepository.save(value);
	}

	@Override
	public Race update(Race value, int id) {
		Race raceData = raceRepository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("")) {
			raceData.setName(value.getName());
		}
		return raceRepository.save(value);
	}

	@Override
	public void delete(int id) {
		raceRepository.deleteById(id);
	}

	@Override
	public Race getByID(int id) {
		Race raceData = raceRepository.findById(id).get();
		return raceData;
	}

	@Override
	public int getID(Race value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Race> getAllData() {
		List<Race> races = (List<Race>) raceRepository.findAll();
		return races;
	}

	@Override
	public Race getByRace(String race) {
		// TODO Auto-generated method stub
		return null;
	}
}
