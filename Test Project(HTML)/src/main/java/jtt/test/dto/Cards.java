package jtt.test.dto;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")

public class Cards {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int card_id;
	private String name;
	private String description;
	private int attack;
	private int defense;
	private int level;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "attribute_id")
	private Attribute attribute;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "race_id")
	private Race race;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "archetype_id")
	private Archetype archetype;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "frame_type_id")
	private FrameType frame_type;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "card_type_id")
	private Card_Type card_type;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private Card_image image;


	public Cards() {}
	
	
public Cards(String name, String description, int attack, int defense, int level, Attribute attribute, Race race, Archetype archetype, FrameType frame_type, Card_Type card_type, Card_image image ) {
	this.name = name;
	this.description = description;
	this.attack = attack;
	this.defense = defense;
	this.level = level;
	this.attribute = attribute;
	this.race = race;
	this.archetype = archetype;
	this.frame_type = frame_type;
	this.card_type = card_type;
	this.image = image;

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
	return attribute;
}


public void setAttribute_id(Attribute attribute) {
	this.attribute = attribute;
}


public Race getRace_id() {
	return race;
}


public void setRace_id(Race race) {
	this.race = race;
}


public Archetype getArchetype_id() {
	return archetype;
}


public void setArchetype_id(Archetype archetype) {
	this.archetype = archetype;
}


public FrameType getFrame_type_id() {
	return frame_type;
}


public void setFrame_type_id(FrameType frame_type) {
	this.frame_type = frame_type;
}


public Card_Type getCard_type_id() {
	return card_type;
}


public void setCard_type_id(Card_Type card_type) {
	this.card_type = card_type;
}


public Card_image getImage_id() {
	return image;
}


public void setImage_id(Card_image image) {
	this.image = image;
}




}
