package com.blogpress.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blogpress.models.User;
import com.blogpress.dao.UserDaoService;
import com.blogpress.exceptions.UserNotFoundException;

@RestController
public class UsersResource {
	
	@Autowired
	UserDaoService userDaoService;
	
	
	/*
	 * Get all users
	 */
	@GetMapping("/user/all")
	public List<User> getAllUsers(){
		return userDaoService.findAll();
	}
	
	
	/*
	 * Get specific user by id
	 */
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id: "+id);
		return user;
	}
	
	/*
	 * Add user by post
	 */
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userDaoService.addUser(user);
		
//This following code will return the location of the user - /user/4 where 4 is the id of the user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

}
