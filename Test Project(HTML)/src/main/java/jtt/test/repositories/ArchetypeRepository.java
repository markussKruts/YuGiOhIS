package jtt.test.repositories;

import org.springframework.data.repository.CrudRepository;

import jtt.test.dto.Archetype;

public interface ArchetypeRepository extends CrudRepository<Archetype, Integer>{}
