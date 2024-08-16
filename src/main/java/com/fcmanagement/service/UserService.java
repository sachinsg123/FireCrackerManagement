package com.fcmanagement.service;

import com.fcmanagement.generic.GenericService;
import com.fcmanagement.model.User;

public interface UserService extends GenericService<User> {

	boolean authenticate(String email, String password);
	
	User authenticate();
	
	User findByEmail(String email);
	
}