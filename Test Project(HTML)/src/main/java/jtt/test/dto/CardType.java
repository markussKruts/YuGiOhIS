package jtt.test.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "card_types")
public class CardType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int card_type_id;
	String name;
	public CardType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CardType(int card_type_id, String name) {
		super();
		this.card_type_id = card_type_id;
		this.name = name;
	}
	public int getCard_type_id() {
		return card_type_id;
	}
	public void setCard_type_id(int card_type_id) {
		this.card_type_id = card_type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
