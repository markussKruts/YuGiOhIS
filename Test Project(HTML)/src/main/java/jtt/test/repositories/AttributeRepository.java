package jtt.test.repositories;

import org.springframework.data.repository.CrudRepository;

import jtt.test.dto.Attribute;


public interface AttributeRepository extends CrudRepository<Attribute, Integer>{}
