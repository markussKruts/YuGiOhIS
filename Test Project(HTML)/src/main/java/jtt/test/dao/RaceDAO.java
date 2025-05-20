package jtt.test.dao;

import jtt.test.dto.Race;

public interface RaceDAO extends GenericDAO<Race>{
	Race getByRace(String race);
}
