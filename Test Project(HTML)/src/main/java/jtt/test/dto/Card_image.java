package jtt.test.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "card_images")


public class Card_image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int image_id;
	private String image_url;

public Card_image() {}


public Card_image(String image_url) {
	this.image_url = image_url;
}




public int getImage_id() {
	return image_id;
}



public void setImage_id(int image_id) {
	this.image_id = image_id;
}
public String getimage_url() {
	return image_url;
}



public void setimage_url(String image_url) {
	this.image_url = image_url;
}
}
