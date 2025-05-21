package jtt.test.repositories;

import org.springframework.data.repository.CrudRepository;

import jtt.test.dto.Card;

public interface CardRepository extends CrudRepository<Card, Integer>{}
