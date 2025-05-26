package jtt.test.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prices")


public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
private int price_id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "card_id")
private Cards card_id;
private int price;


public Price() {}


public Price(Cards card_id,int price) {
	this.card_id = card_id;
	this.price = price;
}

public int getPrice_id() {
	return price_id;
}


public void setPrice_id(int price_id) {
	this.price_id = price_id;
}


public Cards getCard_id() {
	return card_id;
}



public void setCard_id(Cards card_id) {
	this.card_id = card_id;
}



public int getPrice() {
	return price;
}



public void setPrice(int price) {
	this.price = price;
}
}
