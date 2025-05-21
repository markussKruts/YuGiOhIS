package jtt.test.dao;

import java.sql.SQLException;

import jtt.test.dto.Card;
import jtt.test.dto.Price;



public interface  PriceDAO extends GenericDAO<Price> {

	Price getByCard(Card card) throws SQLException;
	Price getByPrice(int price) throws SQLException;
}