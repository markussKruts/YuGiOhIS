package jtt.test.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")

public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int card_id;
	private String name;
	private String description;
	private int attack;
	private int defense;
	private int level;
	private Attribute attribute_id;
	private Race race_id;
	private Archetype archetype_id;
	private FrameType frame_type_id;
	private Card_Type card_type_id;
	private Card_image image_id;


	public Card() {}
	
	
public Card(String name, String description, int attack, int defense, int level, Attribute attribute_id, Race race_id, Archetype archetype_id, FrameType frame_type_id, Card_Type card_type_id, Card_image image_id ) {
	this.name = name;
	this.description = description;
	this.attack = attack;
	this.defense = defense;
	this.level = level;
	this.archetype_id = archetype_id;
	this.attribute_id = attribute_id;
	this.card_type_id = card_type_id;
	this.frame_type_id = frame_type_id;
	this.image_id = image_id;
	this.race_id = race_id;

}




public int getCard_id() {
	return card_id;
}




public void setCard_id(int card_id) {
	this.card_id = card_id;
}




public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getdescription() {
	return description;
}


public void setdescription(String description) {
	this.description = description;
}


public int getAttack() {
	return attack;
}


public void setAttack(int attack) {
	this.attack = attack;
}


public int getDefense() {
	return defense;
}


public void setDefense(int defense) {
	this.defense = defense;
}


public int getLevel() {
	return level;
}


public void setLevel(int level) {
	this.level = level;
}


public Attribute getAttribute_id() {
	return attribute_id;
}


public void setAttribute_id(Attribute attribute_id) {
	this.attribute_id = attribute_id;
}


public Race getRace_id() {
	return race_id;
}


public void setRace_id(Race race_id) {
	this.race_id = race_id;
}


public Archetype getArchetype_id() {
	return archetype_id;
}


public void setArchetype_id(Archetype archetype_id) {
	this.archetype_id = archetype_id;
}


public FrameType getFrame_type_id() {
	return frame_type_id;
}


public void setFrame_type_id(FrameType frame_type_id) {
	this.frame_type_id = frame_type_id;
}


public Card_Type getCard_type_id() {
	return card_type_id;
}


public void setCard_type_id(Card_Type card_type_id) {
	this.card_type_id = card_type_id;
}


public Card_image getImage_id() {
	return image_id;
}


public void setImage_id(Card_image image_id) {
	this.image_id = image_id;
}




}
