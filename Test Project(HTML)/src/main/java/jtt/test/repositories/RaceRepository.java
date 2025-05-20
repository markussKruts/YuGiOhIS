package jtt.test.repositories;

import org.springframework.data.repository.CrudRepository;

import jtt.test.dto.Race;

public interface RaceRepository extends CrudRepository<Race, Integer>{}
