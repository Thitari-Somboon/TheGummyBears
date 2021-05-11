package com.thegummybears.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *[30 - Check Uniqueness of User Email]
 *
 */
@RestController
public class UserRestController {

	@Autowired
	private UserService service;
	
	//Testing by create Bobbie@email.com - bobbie123456
	/*
	 * @PostMapping("/users/check_email") public String
	 * checkDuplicateEmail(@Param("email") String email) { return
	 * service.isEmailUnique(email) ? "OK" : "Duplicated"; }
	 */
	
	//[32 - update]
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {
		return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
	}
}

	
