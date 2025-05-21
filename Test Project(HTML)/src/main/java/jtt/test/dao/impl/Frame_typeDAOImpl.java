package jtt.test.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.Frame_typeDAO;
import jtt.test.dto.FrameType;

import jtt.test.repositories.Frame_typeRepository;
@Service
public class Frame_typeDAOImpl implements Frame_typeDAO {

	@Autowired
	Frame_typeRepository frame_typeRepository;
	
	@Override
	public FrameType insert(FrameType value) {
		return frame_typeRepository.save(value);
	}

	@Override
	public FrameType update(FrameType value, int id) {
		FrameType FrameTypeData = frame_typeRepository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("")) {
			FrameTypeData.setName(value.getName());
		}
		return frame_typeRepository.save(value);
	}

	@Override
	public void delete(int id) {
		frame_typeRepository.deleteById(id);
		
	}

	@Override
	public FrameType getByID(int id) {
		FrameType FrameTypeData = frame_typeRepository.findById(id).get();
		return FrameTypeData;
	}

	@Override
	public int getID(FrameType value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FrameType> getAllData() {
		List<FrameType> FrameTypes = (List<FrameType>) frame_typeRepository.findAll();
		return FrameTypes;
	}

	@Override
	public FrameType getByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
