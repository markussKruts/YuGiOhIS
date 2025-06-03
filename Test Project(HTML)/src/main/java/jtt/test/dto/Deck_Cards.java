package jtt.test.dto;

import java.util.List; // This import is not strictly needed for the fix, but good practice if you had other List fields
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; // Import for @JoinColumn
import jakarta.persistence.ManyToOne;   // Import for @ManyToOne
import jakarta.persistence.Table;

@Entity
@Table(name = "deck_cards")
public class Deck_Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int card_deck_id;

    // This defines the Many-to-One relationship with Decks
    @ManyToOne
    @JoinColumn(name = "deck_id") // This specifies the foreign key column in the deck_cards table that refers to the Decks table's primary key
    private Decks deck; // Changed variable name from 'decks' to 'deck' to reflect a single ManyToOne relationship

    // This defines the Many-to-One relationship with Card
    @ManyToOne
    @JoinColumn(name = "card_id") // This specifies the foreign key column in the deck_cards table that refers to the Card table's primary key
    private Card card; // Changed variable name from 'cards' to 'card' to reflect a single ManyToOne relationship

    public Deck_Cards() {}

    // Getters and Setters for deck
    public Decks getDeck() {
        return deck;
    }

    public void setDeck(Decks deck) {
        this.deck = deck;
    }

    // Getters and Setters for card
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getCard_deck_id() {
        return card_deck_id;
    }

    public void setCard_deck_id(int card_deck_id) {
        this.card_deck_id = card_deck_id;
    }
}