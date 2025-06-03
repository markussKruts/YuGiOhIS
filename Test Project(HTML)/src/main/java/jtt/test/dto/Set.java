package jtt.test.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "sets")
public class Set {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int set_id;
private String name;
private String code;
private Date release_date;
@ManyToOne
@JoinColumn(name = "image_id")
private Card_image image;


public Set() {}

public Set( String name, String code, Date release_date) {
	
	this.name = name;
	this.code = code;
	this.release_date = release_date;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public Date getRelease_date() {
	return release_date;
}

public void setRelease_date(Date release_date) {
	this.release_date = release_date;
}

public int getId() {
	return set_id;
}

public void setId(int set_id) {
	this.set_id = set_id;
}

public Card_image getImage() {
	return image;
}

public void setImage(Card_image image) {
	this.image = image;
}


}

