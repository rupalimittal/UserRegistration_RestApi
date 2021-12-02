package com.springcraft.springcraft.service;

import java.util.List;

import com.springcraft.springcraft.entity.User;

public interface UserService {
	List<User> getAllUsers();
	
	User saveUser(User user);
	
	User getUserById(Long id);
	
	User updateUser(Long id,User user);
	
	void deleteUserById(Long id);
}
