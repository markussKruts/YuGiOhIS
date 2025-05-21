package jtt.test.repositories;

import org.springframework.data.repository.CrudRepository;

import jtt.test.dto.Price;

public interface PriceRepository extends CrudRepository<Price, Integer>{}
