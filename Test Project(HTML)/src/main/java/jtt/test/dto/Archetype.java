package jtt.test.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "archetypes")

public class Archetype {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int archetype_id;
	private String name;
	
	
	public Archetype() {}
	

	public Archetype(String name) {
		this.name = name;
		
	}

	public int getArchetype_id() {
		return archetype_id;
	}


	public void setArchetype_id(int archetype_id) {
		this.archetype_id = archetype_id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	}
