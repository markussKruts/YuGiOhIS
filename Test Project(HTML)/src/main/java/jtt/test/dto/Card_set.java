package jtt.test.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "card_sets")


public class Card_set {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name ="card_set_id")
private int card_set_id;
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "card_id")
private Card card_id;
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "set_id")
private Set set_id;
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "rarity_id")
private Rarities rarity_id;


public Card_set(Card card_id,Set set_id,Rarities rarity_id) {
	this.card_id = card_id;
	this.rarity_id = rarity_id;
	this.set_id = set_id;
}
public Card_set() {
	// TODO Auto-generated constructor stub
}
public int getId() {
	return card_set_id;
}

public void setId(int card_set_id) {
	this.card_set_id = card_set_id;
}

public Card getCard_id() {
	return card_id;
}

public void setCard_id(Card card_id) {
	this.card_id = card_id;
}

public Set getSet_id() {
	return set_id;
}

public void setSet_id(Set set_id) {
	this.set_id = set_id;
}

public Rarities getRarity_id() {
	return rarity_id;
}

public void setRarity_id(Rarities rarity_id) {
	this.rarity_id = rarity_id;
}
}
