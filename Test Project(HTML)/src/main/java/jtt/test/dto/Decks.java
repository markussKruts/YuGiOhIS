package jtt.test.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "decks")
public class Decks {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int deck_id;
	private String name;
	@OneToMany(mappedBy = "decks")
	private List<User> users;
	
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
}
