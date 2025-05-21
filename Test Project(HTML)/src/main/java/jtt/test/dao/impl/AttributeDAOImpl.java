package jtt.test.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.AttributeDAO;

import jtt.test.dto.Attribute;

import jtt.test.repositories.AttributeRepository;
@Service
public class AttributeDAOImpl implements AttributeDAO {
	@Autowired
	AttributeRepository attributeRepository;
	
	@Override
	public Attribute insert(Attribute value) {
		return attributeRepository.save(value);
	}

	@Override
	public Attribute update(Attribute value, int id) {
		Attribute attributeData = attributeRepository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("")) {
			attributeData.setName(value.getName());
		}
		return attributeRepository.save(value);
	}

	@Override
	public void delete(int id) {
		attributeRepository.deleteById(id);
	}

	@Override
	public Attribute getByID(int id) {
		Attribute attributeData = attributeRepository.findById(id).get();
		return attributeData;
	}

	@Override
	public int getID(Attribute value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Attribute> getAllData() {
		List<Attribute> attributes = (List<Attribute>) attributeRepository.findAll();
		return attributes;
	}

	@Override
	public Attribute getByName(String name)  {
		// TODO Auto-generated method stub
		return null;
	}

	
}
