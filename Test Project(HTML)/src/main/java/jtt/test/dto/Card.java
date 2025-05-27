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

public class Card {
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
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;


	public Card() {}
	
	
public Card(String name, String description, int attack, int defense, int level, Attribute attribute, Race race, Archetype archetype, FrameType frame_type, Card_Type card_type, Card_image image, User user) {
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
	this.user = user;
}




public int getCard_id() {
	return card_id;
}




public void setCard_id(int card_id) {
	this.card_id  = card_id;
}




public User getUser() {
	return user;
}


public void setUser(User user) {
	this.user = user;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
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


public Attribute getAttribute () {
	return attribute;
}


public void setAttribute (Attribute attribute) {
	this.attribute = attribute;
}


public Race getRace () {
	return race;
}


public void setRace (Race race) {
	this.race = race;
}


public Archetype getArchetype () {
	return archetype;
}


public void setArchetype (Archetype archetype) {
	this.archetype = archetype;
}


public FrameType getFrame_type () {
	return frame_type;
}


public void setFrame_type (FrameType frame_type) {
	this.frame_type = frame_type;
}


public Card_Type getCard_type () {
	return card_type;
}


public void setCard_type (Card_Type card_type) {
	this.card_type = card_type;
}


public Card_image getImage () {
	return image;
}


public void setImage (Card_image image) {
	this.image = image;
}




}
