package jtt.test.dto;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "archetypes")

public class Archetype {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int archetype_id;
	private String name;
	@OneToMany(mappedBy = "archetype")
	private List<Card> cards;
	
	public Archetype(String name) {
		this.name = name;
	}
	public Archetype() {}

	public int getId() {
		return archetype_id;
	}


	public void setId(int archetype_id) {
		this.archetype_id = archetype_id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	}
