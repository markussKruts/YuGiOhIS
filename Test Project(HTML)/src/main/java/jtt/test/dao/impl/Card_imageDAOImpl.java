package jtt.test.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.Card_imageDAO;
import jtt.test.dto.Card_image;
import jtt.test.repositories.Card_imageRepository;

@Service
public class Card_imageDAOImpl implements Card_imageDAO{
	@Autowired
	Card_imageRepository imageRepository;
	
	@Override
	public Card_image insert(Card_image value) {
		return imageRepository.save(value);
	}

	@Override
	public Card_image update(Card_image value, int id) {
		Card_image Card_imageData = imageRepository.findById(id).get();
		if(Objects.nonNull(value.getImage())) {
			Card_imageData.setImage(value.getImage());
		}
		return imageRepository.save(value);
	}

	@Override
	public void delete(int id) {
		imageRepository.deleteById(id);
	}

	@Override
	public Card_image getByID(int id) {
		Card_image Card_imageData = imageRepository.findById(id).get();
		return Card_imageData;
	}

	@Override
	public int getID(Card_image value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Card_image> getAllData() {
		List<Card_image> Card_images = (List<Card_image>) imageRepository.findAll();
		return Card_images;
	}

	@Override
	public Card_image getByImage(byte[] img)  {
		return imageRepository.findByImage(img);
	}

	@Override
	public Card_image getByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return imageRepository.findByName(name);
	}
}
