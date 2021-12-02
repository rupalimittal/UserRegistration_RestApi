package com.springcraft.springcraft;

import com.springcraft.springcraft.entity.User;
import com.springcraft.springcraft.repository.UserRepository;
import com.springcraft.springcraft.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTests {
	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	private final Long userId = 1L;
	private final String firstName = "rupali";
	private final String lastName = "mittal";
	private final String userEmail = "rupali.mittal@gmail.com";
	private final String userPhone = "9999999999";
	private final Long userId1 = 2L;
	private final String firstName1 = "testfirstname";
	private final String lastName1 = "testlastname";
	private final String userEmail1 = "test.test@gmail.com";
	private final String userPhone1 = "9999999999";


	private  List<User> users = new ArrayList<>();
	User user1 = new User();
	User user2 = new User();

	@Before
	public List<User> getUsersList() {

		user1.setId(userId);
		user1.setFirstName(firstName);
		user1.setLastName(lastName);
		user1.setEmail(userEmail);
		user1.setPhoneNumber(userPhone);

		user2 = new User();
		user2.setId(userId1);
		user2.setFirstName(firstName1);
		user2.setLastName(lastName1);
		user2.setEmail(userEmail1);
		user2.setPhoneNumber(userPhone1);
		users.add(user1);
		users.add(user2);
		return users;
	}

	@Test
	public void testGetAllUsers() {
		Mockito.when(userRepository.findAll()).thenReturn(getUsersList());
		List<User> findUsers = userService.getAllUsers();
		assertNotNull(findUsers);
		assertEquals(2, findUsers.size());
	}

	@Test
	public void testSaveUsers() {
		Mockito.when(userRepository.save(user1)).thenReturn(user1);
		User user = userService.saveUser(user1);
		assertNotNull(user);
		assertEquals(user1.getId(), user.getId());
		assertEquals(user1.getFirstName(), user.getFirstName());
		assertEquals(user1.getLastName(), user.getLastName());
		assertEquals(user1.getEmail(), user.getEmail());
		assertEquals(user1.getPhoneNumber(), user.getPhoneNumber());

	}

	@Test
	public void testGetUserById() {
		Mockito.when(userRepository.findById(userId1)).thenReturn(Optional.of(user1));
		User user = userService.getUserById(userId1);
		assertNotNull(user);
		assertEquals(user1.getId(), user.getId());
		assertEquals(user1.getFirstName(), user.getFirstName());
		assertEquals(user1.getLastName(), user.getLastName());
		assertEquals(user1.getEmail(), user.getEmail());
		assertEquals(user1.getPhoneNumber(), user.getPhoneNumber());
	}

	@Test
	public void testUpdateUser() {

		User updatedUser = new User();
		updatedUser.setId(userId1);
		updatedUser.setFirstName("updatedname");
		updatedUser.setLastName("lastname");
		updatedUser.setEmail(userEmail1);
		updatedUser.setPhoneNumber(userPhone1);

		Mockito.when(userRepository.findById(userId1)).thenReturn(Optional.of(user1));
		Mockito.when(userRepository.save(user1)).thenReturn(user1);
		User existingUser = userService.getUserById(userId1);
		existingUser.setId(updatedUser.getId());
		existingUser.setFirstName(updatedUser.getFirstName());
		existingUser.setLastName(updatedUser.getLastName());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
		User user = userService.updateUser(userId1,updatedUser);
		assertNotNull(user);
		assertEquals(updatedUser.getId(), user.getId());
		assertEquals(updatedUser.getFirstName(), user.getFirstName());
		assertEquals(updatedUser.getLastName(), user.getLastName());
		assertEquals(updatedUser.getEmail(), user.getEmail());
		assertEquals(updatedUser.getPhoneNumber(), user.getPhoneNumber());
	}

	@Test
	public void testDeleteUserById() {
		userService.deleteUserById(userId);
		Mockito.verify(userRepository, Mockito.times(1))
				.deleteById(userId);
	}




}
