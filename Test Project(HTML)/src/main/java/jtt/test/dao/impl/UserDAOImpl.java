package jtt.test.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.test.dao.UserDAO;
import jtt.test.dto.Race;
import jtt.test.dto.User;
import jtt.test.repositories.UserRepository;

@Service
public class UserDAOImpl implements UserDAO{
	@Autowired
	UserRepository userRepository;

	@Override
	public User insert(User value) {
		return userRepository.save(value);
	}

	@Override
	public User update(User value, int id) {
		User userData = userRepository.findById(id).get();
		if(Objects.nonNull(value.getName()) && !value.getName().equals("")) {
			userData.setName(value.getName());
			userData.setEmail(value.getEmail());
			userData.setPassword(value.getPassword());
			userData.setUsername(value.getUsername());
		}
		return userRepository.save(value);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getByID(int id) {
		User userData = userRepository.findById(id).get();
		return userData;
	}

	@Override
	public int getID(User value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getAllData() {
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	@Override
	public User getByUsername(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
