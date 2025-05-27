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
	private byte[] image;
	@OneToMany(mappedBy = "image")
	private List<Card> cards;
	
public Card_image() {}


public Card_image(byte[] image) {
	this.image = image;
}




public int getImage_id() {
	return image_id;
}



public void setImage_id(int image_id) {
	this.image_id = image_id;
}
public byte[] getImage() {
	return image;
}



public void setImage(byte[] image) {
	this.image = image;
}
}
