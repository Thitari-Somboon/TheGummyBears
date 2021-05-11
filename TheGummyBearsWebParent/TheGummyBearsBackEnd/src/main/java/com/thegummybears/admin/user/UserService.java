package com.thegummybears.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thegummybears.common.entity.Role;
import com.thegummybears.common.entity.User;

@Service
@Transactional
public class UserService {

	//[27]
	@Autowired
	private UserRepository userRepo; //update param reop -> userRepo
	
	//[28]
	@Autowired
	private RoleRepository roleRepo;
	
	//[27]
	public List<User> listAll(){
		return (List<User>) userRepo.findAll();
	}
	
	//[28]
	public List<Role> listRoles() {
		return (List<Role>) roleRepo.findAll();
	}

	//[28]-add ready to test creating new user
	public void save(User user) {
		//[28]- userRepo.save(user);
		/*
		*[29 - Encode Password User] - add ready to test
		*encoderPassword(user);
		*userRepo.save(user); //fah@email.com - fah123456
		*/
		
		//[32 Update ciode]
		boolean isUpdatingUser = (user.getId() != null);
		
		if(isUpdatingUser) {
			User existingUser =  userRepo.findById(user.getId()).get();
			
			if(user.getPassword().isEmpty()) {
				user.setPassword(existingUser.getPassword());
			}else {
				encoderPassword(user);
			}
		}else {
		encoderPassword(user);
		}
		
		userRepo.save(user);
	}
	
	//[29]
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//[29]
	private void encoderPassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
	
	//[30 - Check Uniqueness of User Email]
	//When creating a new user
	/*
	 * public boolean isEmailUnique(String email) { User userByEmail =
	 * userRepo.getUserByEmail(email); return userByEmail == null; }
	 */
	//[32 - Code Update USer function -> update userRestController ]
		public boolean isEmailUnique(Integer id, String email) { 
			User userByEmail = userRepo.getUserByEmail(email);
			if(userByEmail == null) return true;
			
			boolean isCreatingNew = (id == null);
			if(isCreatingNew) {
				if(userByEmail != null) return false;
			}else {
				//Email is not unique
				if(userByEmail.getId() != id) {
					return false;
				}
			}
			return true;
		}
		
		
	//[32 - Code Update USer function]
	public User get(Integer id) throws UserNotFoundException {
		try {
		return userRepo.findById(id).get();
		}catch (NoSuchElementException e) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
	}
	
	//[33 - Delete user]
	public void delete(Integer id) throws UserNotFoundException {
		Long countById = userRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
		userRepo.deleteById(id);
	}
	
	
	//[34 - Update user enabled status]
	public void updateUserEnableStatus(Integer id, boolean enabled) {
		userRepo.updateEnabledStatus(id, enabled);
	}
}
