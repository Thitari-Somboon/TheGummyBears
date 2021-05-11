package com.thegummybears.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thegummybears.common.entity.Role;
import com.thegummybears.common.entity.User;

/**
 * 
 * [27]
 *
 */

@Controller
public class UserController {
	
	//[27]
	@Autowired
	private UserService service;

	//[27]
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	
	//[28]
	@GetMapping("/users/new")
	public String newUser(Model model) {
		
		List<Role> listRoles = service.listRoles();
		User user = new User();
		user.setEnabled(true); //for ennabled check box
		
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		//[32 - Set page Title]
		model.addAttribute("pageTitle", "Create New User");
		
		return "user_form";
	}
	
	//[28]
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		System.out.println(user);
		 service.save(user);
		
		 redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");

		return "redirect:/users";
	}
	
	//[32 - Code Update User function]
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable(name = "id") Integer id,  
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		
		model.addAttribute("user", user);
		
		  model.addAttribute("pageTitle", "Edit User (ID: " + id+ ")");
		  model.addAttribute("listRoles", listRoles);
		 
		
		return "user_form";
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/users";
		}
	}
	
	//[33 - Delete user function]
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id,  
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
			service.delete(id);
			redirectAttributes.addFlashAttribute("meesage",  "The user ID " + id + " has been deleted successfully");
			} catch (UserNotFoundException e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
			}
		return "redirect:/users";
	}
	
	//[34 - Update user enabled status]
	@GetMapping("/users/{id}/enabled/{status}")
	public String updateUserEnabledStatus(@PathVariable("id") Integer id,
			@PathVariable("status") boolean enabled, 
			RedirectAttributes redirectAttributes) {
		service.updateUserEnableStatus(id, enabled);
		String status = enabled ?  "enabled" : "disabled";
		String message = "The user id " + id  + " has been "+ status;
		
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/users";
	} 
	
}
