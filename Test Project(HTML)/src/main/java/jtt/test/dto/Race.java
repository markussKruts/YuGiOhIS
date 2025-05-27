package jtt.test.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "races")

public class Race {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int race_id;
	private	String name;
	@OneToMany(mappedBy = "race")
	private List<Card> card;
	
	public Race() {}
	
	public Race(String name) {
		this.name = name;
	}


	public int getRace_id() {
		return race_id;
	}


	public void setRace_id(int race_id) {
		this.race_id = race_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	}
