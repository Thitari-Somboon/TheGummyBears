package com.thegummybears.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	
	//[48 user login]
	  @GetMapping("/login") public String viewLoginPage() { return "login"; }
	 
}
