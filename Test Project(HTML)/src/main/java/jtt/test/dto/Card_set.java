package jtt.test.dto;

import java.util.List;

import jakarta.persistence.OneToMany;

public class Card_set {
	private int card_sets;
private Card card_id;
private Set set_id;
private Rarities rarity_id;


public Card_set(Card card_id,Set set_id,Rarities rarity_id) {
	this.card_id = card_id;
	this.rarity_id = rarity_id;
	this.set_id = set_id;
}
public int getCard_sets() {
	return card_sets;
}

public void setCard_sets(int card_sets) {
	this.card_sets = card_sets;
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
