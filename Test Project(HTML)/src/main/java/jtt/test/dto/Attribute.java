package jtt.test.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "attributes")

public class Attribute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int attribute_id;
private String name;
@OneToMany(mappedBy = "attribute")
private List<Card> cards;

	public Attribute() {}

public Attribute(String name) {
	this.name = name;
	
}



public int getAttribute_id() {
return attribute_id;
}


public void setAttribute_id(int attribute_id) {
this.attribute_id = attribute_id;
}

public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


}
