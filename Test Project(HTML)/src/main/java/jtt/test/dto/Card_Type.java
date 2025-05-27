package jtt.test.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "card_types")


public class Card_Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int card_type_id;
	private String name;
	@OneToMany(mappedBy = "card_type")
	private List<Card> cards;
	
	
	public Card_Type() {}
	

	public Card_Type(String name) {
		this.name = name;
		
	}


	public int getCard_type() {
		return card_type_id;
	}


	public void setCard_type(int card_type_id) {
		this.card_type_id = card_type_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	}

