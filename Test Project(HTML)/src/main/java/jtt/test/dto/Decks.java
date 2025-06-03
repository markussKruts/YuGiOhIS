package jtt.test.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "decks")
public class Decks {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int deck_id;
	private String name;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	public Decks() {}
	
	public Decks(String name) {
		this.name = name;
	}

	public int getDeck_id() {
		return deck_id;
	}

	public void setDeck_id(int deck_id) {
		this.deck_id = deck_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}
	
	
	
}
