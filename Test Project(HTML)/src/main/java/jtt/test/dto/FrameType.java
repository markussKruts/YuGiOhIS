package jtt.test.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "frame_types")



public class FrameType {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
private int frame_type_id;
private	String name;
@OneToMany(mappedBy = "frame_type")
private List<Card> cards;

	public FrameType() {
		// TODO Auto-generated constructor stub
	}
	
	
	public FrameType(String name) {
		this.name = name;
		
	}


	public int getFrame_type() {
		return frame_type_id;
	}


	public void setFrame_type(int frame_type) {
		this.frame_type_id = frame_type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	}

