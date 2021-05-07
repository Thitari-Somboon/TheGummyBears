package com.thegummybears.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.thegummybears.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	

}
