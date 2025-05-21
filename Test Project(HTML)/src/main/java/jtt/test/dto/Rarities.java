package jtt.test.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rarities")


public class Rarities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
private int rarity_id;
private String name;


public Rarities() {}

	public Rarities(String name) {
		this.name = name;
		
	}


	public int getRarity_id() {
		return rarity_id;
	}


	public void setRarity_id(int rarity_id) {
		this.rarity_id = rarity_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	}
