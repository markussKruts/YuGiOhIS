package jtt.test.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private	String username;
	private String email;
	private String password;
	private String name;
	@OneToMany(mappedBy = "user")
	private List<Card> cards;
	@OneToMany(mappedBy = "user")
	private List<Decks> decks;
	@OneToMany(mappedBy = "user")
	private List<Set> set;
	
	public User(int user_id, String username, String email, String password, String name) {
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public User() {}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

