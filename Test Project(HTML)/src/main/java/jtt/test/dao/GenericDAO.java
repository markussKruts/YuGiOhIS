package jtt.test.dao;

import java.util.List;
import java.util.Optional;

import jtt.test.dto.Card;

public interface GenericDAO<T>{
	T insert(T value);
	T update(T value, int id);
	void delete(int id);
	T getByID(int id);
	int getID(T value);
	List<T> getAllData();

}
