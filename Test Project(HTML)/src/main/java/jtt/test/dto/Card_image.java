package jtt.test.dto;

import java.sql.Blob;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "card_images")


public class Card_image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="image_id")
	private int image_id;
	private String name;
	private byte[] image;
	private String content_type;
	@OneToMany(mappedBy = "image")
	private List<Card> cards;
	
public Card_image() {}


public String getContent_type() {
	return content_type;
}


public void setContent_type(String content_type) {
	this.content_type = content_type;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public List<Card> getCards() {
	return cards;
}


public void setCards(List<Card> cards) {
	this.cards = cards;
}


public Card_image(byte[] image, String name, String content_type) {
	this.image = image;
	this.name = name;
	this.content_type = content_type;
}




public int getId() {
	return image_id;
}



public void setId(int image_id) {
	this.image_id = image_id;
}
public byte[] getImage() {
	return image;
}



public void setImage(byte[] image) {
	this.image = image;
}
}
