package com.thegummybears.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.thegummybears.common.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;

	//@GetMapping("/users")
//	public String listAll(Model model) {
//		List<User> listUsers = service.listAll()
	//}
}
