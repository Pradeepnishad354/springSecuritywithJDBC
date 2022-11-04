package com.security.orm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.orm.model.User;
import com.security.orm.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userSerive;
	// show register user

	@GetMapping("/reg")
	public String showReg() {

		return "userRegister";
	}

	// Read form data for save operation

	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user, Model model) {

		Integer id = userSerive.saveUser(user);

		String msg = "User" + id + " " + "Save";

		model.addAttribute("message", msg);

		return "UserRegister";

	}

}
