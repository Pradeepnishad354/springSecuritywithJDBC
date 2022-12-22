package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.entity.User;
import com.practice.service.UserService;
import com.practice.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {


	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping
	public String showRegisterForm(Model model) {

		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}

	@PostMapping
	public String RegisterUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {

		userService.save(registrationDto);

		return "redirect:/registration?success";
	}

	@GetMapping("/user")
	public String listUser(Model model) {

		model.addAttribute("users", userService.getAllUser());
		return "user";

	}

	@GetMapping("/user/{id}")
	public String deleteById(@PathVariable("id") long id) {

		userService.deleteById(id);
		return "redirect/user";

	}

	@GetMapping("/user/edit/{id}")
	public String editUserForm(@PathVariable long id, Model model) {

		model.addAttribute("user", userService.getUserById(id));
		return "edit_user";
	}

	@PostMapping("/user/{id}")
	public String updateUser(@PathVariable long id, @ModelAttribute("user") User user, Model model) {

		// get user by id
		User existingUser = userService.getUserById(id);
		existingUser.setFirstname(user.getFirstname());
		existingUser.setLastname(user.getLastname());
		existingUser.setEmail(user.getEmail());

		userService.updateUser(existingUser);
		return "redirect:/registration";

	}

}
