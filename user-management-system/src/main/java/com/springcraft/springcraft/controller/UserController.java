package com.springcraft.springcraft.controller;

import com.springcraft.springcraft.Constants.constants;
import com.springcraft.springcraft.exceptions.customException.BadRequestException;
import com.springcraft.springcraft.validations.ApiRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springcraft.springcraft.entity.User;
import com.springcraft.springcraft.service.UserService;

import java.text.ParseException;

@Controller
public class UserController {
	@Autowired
	@Qualifier("saveNewUserRequestValidator")
	private ApiRequestValidator<User> requestValidator;
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	// handler method to handle list users and return mode and view
	@GetMapping("/users")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}
	
	@GetMapping("/users/new")
	public String createUserForm(Model model) {
		
		// create user object to hold user form data
		User user = new User();
		model.addAttribute("user", user);
		return "create_user";
		
	}
	
	@PostMapping("/users")
	//to bind form data to user we use @ModelAttribute
	public String saveUser(@ModelAttribute("user") User user) throws ParseException {
		String validationResponse = requestValidator.isRequestValid(user);
		if(!validationResponse.equals(constants.Success))
		{
			throw new BadRequestException(validationResponse);
		}

		userService.saveUser(user);
		return "redirect:/users"; //redirect to users page
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUserForm(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "edit_user";
	}

	@PostMapping("/users/{id}")
	public String updateUser(@PathVariable Long id,
			@ModelAttribute("user") User user,
			Model model) throws ParseException {
		String validationResponse = requestValidator.isRequestValid(user);
		if(!validationResponse.equals(constants.Success))
		{
			throw new BadRequestException(validationResponse);
		}
		// save updated user object
		userService.updateUser(id,user);
		return "redirect:/users";		
	}
	
	// handler method to handle delete user request
	@GetMapping("/users/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		return "redirect:/users";
	}
	
}
