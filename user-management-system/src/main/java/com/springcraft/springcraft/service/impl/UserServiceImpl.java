package com.springcraft.springcraft.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.springcraft.springcraft.entity.User;
import com.springcraft.springcraft.repository.UserRepository;
import com.springcraft.springcraft.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(Long id, User user) {
		// get user from database by id
		User existingUser = getUserById(id);
		existingUser.setId(id);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPhoneNumber(user.getPhoneNumber());
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}


}
