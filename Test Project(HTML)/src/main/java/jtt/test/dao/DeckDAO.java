package jtt.test.dao;

import java.sql.SQLException;
import java.util.List;

import jtt.test.dto.Deck;
import jtt.test.dto.Set;
import jtt.test.dto.User;

public interface DeckDAO extends GenericDAO<Deck> {
	
	List<Deck> getByUser(User user) throws SQLException;
}
