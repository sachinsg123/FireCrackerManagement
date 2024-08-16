package com.fcmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcmanagement.model.User;
import com.fcmanagement.repositories.UserRepository;
import com.fcmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private User user1 = null;
	
	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public User find(Integer id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public User update(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean authenticate(String email, String password){
		User user = this.findByEmail(email);
		if(user == null){
			return false;
		}else{
			if(password.equals(user.getPassword()) && ("Active".equals(user.getStatus()))) {
				user1 = user;
				return true;
			}
			else return false;
		}
	}

	@Override
	public User authenticate(){
		return user1;
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
