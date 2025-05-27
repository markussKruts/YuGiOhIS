package jtt.test.dao;

import java.sql.SQLException;


import jtt.test.dto.Card_image;

public interface Card_imageDAO extends GenericDAO<Card_image> {
	Card_image getByImage(byte[] image) throws SQLException;
	Card_image getByName(String name) throws SQLException;
}
