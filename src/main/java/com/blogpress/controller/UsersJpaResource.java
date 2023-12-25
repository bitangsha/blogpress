package com.blogpress.controller;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blogpress.models.SomeBean;
import com.blogpress.models.User;
import com.blogpress.repositories.UserRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

import com.blogpress.dao.UserDaoService;
import com.blogpress.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/usersjpa")
public class UsersJpaResource {
	
	@Autowired
	UserRepository userRepository;
	
	
	/*
	 * Get all users
	 */
	//@GetMapping("/")
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//Hateoas implementation - Wrapping user class to entity model
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<User> getUserHateoas(@PathVariable int id) {
		User user = userRepository.findById(id).get();
		if(user==null)
			throw new UserNotFoundException("id: "+id);
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		
		entityModel.add(link.withRel("all-users"));
		
		
		return entityModel;
	}
	
	/*
	 * Get specific user by id
	 */
	@GetMapping("/{id}")
	public User getUser(@PathVariable int id) {
		User user = userRepository.findById(id).get();
		if(user==null)
			throw new UserNotFoundException("id: "+id);
		return user;
	}
	
	/*
	 * Delete specific user by id
	 */
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	/*
	 * Add user by post
	 */
	
	//@PostMapping("/")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
//This following code will return the location of the user - /users/4 where 4 is the id of the user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/*
	 * Dynamic filtering
	 */
	@GetMapping("/filtered-users")
	public MappingJacksonValue filtering1() {
		SomeBean someBean = new SomeBean("val1","val2","val3");
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
		
	}

	
	
}
