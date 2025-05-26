package jtt.test.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


	@Entity
	@Table(name = "card_images")
	public class CardImage {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		int image_id;
		String image_url;
		
		
		public CardImage() {
		}


		public CardImage(int image_id, String image_url) {
			this.image_id = image_id;
			this.image_url = image_url;
		}


		public int getImage_id() {
			return image_id;
		}


		public void setImage_id(int image_id) {
			this.image_id = image_id;
		}


		public String getImage_url() {
			return image_url;
		}


		public void setImage_url(String image_url) {
			this.image_url = image_url;
		}
		
		
		
	}
