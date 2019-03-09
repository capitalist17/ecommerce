package com.bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.service.UserService;
import com.bookstore.utility.SecurityUtility;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner{
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			// Every time the app starts it will try to create this hardcoded user. The first time it succeeds.
			// Subsequently it fails and the app wont start. So this try catch to absorb the exception silently
			User user1 = new User();
			user1.setFirstName("John");
			user1.setLastName("Doe");
			user1.setUsername("john");
			user1.setPassword(SecurityUtility.passwordEncoder().encode("pass"));
			user1.setEmail("Jdoe@gmail.com");
			Set<UserRole> userRoles = new HashSet<>();
			Role role1= new Role();
			role1.setRoleId(1);
			role1.setName("ROLE_USER");
			userRoles.add(new UserRole(user1, role1));
			
			userService.createUser(user1, userRoles);
		} catch (Exception e) {
			System.out.println("The user is already present : " + e);
		}
		
	}

}
