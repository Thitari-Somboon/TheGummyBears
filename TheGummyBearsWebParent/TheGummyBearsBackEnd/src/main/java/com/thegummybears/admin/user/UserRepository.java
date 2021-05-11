package com.thegummybears.admin.user;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.thegummybears.common.entity.User;

/**
 * 
 * [30 - Check Uniqueness of User Email ]
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {
	
	
	//[30 - Check Uniqueness of User Email]
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	
	//[33 - Delete user]
	public Long countById(Integer id); 
	
	
	//[34 - Update user enabled status]
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);

}