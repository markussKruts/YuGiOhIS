package jtt.test.repositories;

import org.springframework.data.repository.CrudRepository;

import jtt.test.dto.User;

public interface UserRepository extends CrudRepository<User, Integer>{}