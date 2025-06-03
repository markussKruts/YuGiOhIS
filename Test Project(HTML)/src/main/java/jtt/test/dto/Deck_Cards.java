package jtt.test.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "deck_cards")


public class Deck_Cards {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@OneToMany(mappedBy = "deck_cards")
	private List<Decks> decks;
	@OneToMany(mappedBy = "deck_cards")
	private List<Card> cards;
	
	public Deck_Cards() {}

	public List<Decks> getDecks() {
		return decks;
	}

	public void setDecks(List<Decks> decks) {
		this.decks = decks;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	

	
	
}
