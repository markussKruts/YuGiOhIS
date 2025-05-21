package jtt.test.dto;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "sets")
public class Set {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_set;
private String name;
private String code;
private Date release_date;

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

public int getId_set() {
	return id_set;
}

public void setId_set(int id_set) {
	this.id_set = id_set;
}
}

