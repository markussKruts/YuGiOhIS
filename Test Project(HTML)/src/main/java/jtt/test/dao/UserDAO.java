package jtt.test.dao;

import jtt.test.dto.User;

public interface UserDAO extends GenericDAO<User>{
	User getByUsername(String user);
}
