package com.security.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
	
	@RequestMapping("/home")
	public String Home() {
		
		return "homePage";
	}
	
	
	@GetMapping("/welcome")
	public String welcome() {
		
		return "welcomePage";
	}

	@GetMapping("/admin")
	public String admin() {
		
		return "adminPage";
	}
	
	@GetMapping("/student")
	public String student() {
		
		return "studentPage";
	}
	
	@GetMapping("/employee")
	public String Emp() {
		
		return "employeePage";
	}
	
	@GetMapping("/denied")
	public String Denied() {
		
		return "deniedPage";
	}
}


