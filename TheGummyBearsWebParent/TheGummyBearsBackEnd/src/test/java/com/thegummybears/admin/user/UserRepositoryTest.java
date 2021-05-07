package com.thegummybears.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.thegummybears.common.entity.Role;
import com.thegummybears.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager enitiyManager;
	
	//Run Junit with empty test to create the table in DB
	//It will create 
	//1 users table and 
	//2 users_roles table
	//-------------------
	//Update method
	/*
	 * @Test public void testCreateUser() { Role roleAdmin =
	 * enitiyManager.find(Role.class, 1); //Assign to be Admin User userThitari =
	 * new User("Thitari@gmail.com", "password123", "Thitari", "Somboon", 110.0);
	 * userThitari.addRole(roleAdmin);
	 * 
	 * User savedUser = repo.save(userThitari);
	 * assertThat(savedUser.getId()).isGreaterThan(0); //Object save as persistant
	 * object }
	 * 
	 */	
	@Test
	public void testCreateNewUserwithOneRole() {
		Role roleAdmin = enitiyManager.find(Role.class, 1); //Assign to be Admin 
		User userThitari = new User("Thitari@gmail.com", "password123", "Thitari", "Somboon", 110.0);	
		userThitari.addRole(roleAdmin);
		
		User savedUser =  repo.save(userThitari);
		assertThat(savedUser.getId()).isGreaterThan(0); //Object save as persistant object
	}
	
	@Test
	public void testCreateNewUserwithTwoRoles() {
		User userOla = new User("Ola@gmail.com", "password000", "Olga", "Osipova", 250.00);	
		Role roleAdmin = new Role(1);
		Role roleEmployee = new Role(2);
		userOla.addRole(roleAdmin);
		userOla.addRole(roleEmployee);
		
		User savedUser =  repo.save(userOla);
		assertThat(savedUser.getId()).isGreaterThan(0); //Object save as persistant object
	}

}
