package com.blogpress.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blogpress.models.Post;
import com.blogpress.models.User;
import com.blogpress.repositories.PostRepository;
import com.blogpress.repositories.UserRepository;

import jakarta.validation.Valid;

import com.blogpress.exceptions.PostNotFoundException;
import com.blogpress.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/postsjpa")
public class PostJpaResource {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	//Hateoas implementation - Wrapping user class to entity model
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Post> getUserHateoas(@PathVariable int id) {
		Post post = postRepository.findById(id).get();
		if(post==null)
			throw new UserNotFoundException("id: "+id);
		
		EntityModel<Post> entityModel = EntityModel.of(post);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllPosts());
		
		entityModel.add(link.withRel("all-posts"));
		
		
		return entityModel;
	}
	
	
	@GetMapping("/{id}")
	public Post getUser(@PathVariable int id) {
		Post post = postRepository.findById(id).get();
		if(post==null)
			throw new PostNotFoundException("id: "+id);
		return post;
	}
	
	@GetMapping("/user/{id}/posts")
	public List<Post> getPostForUser(@PathVariable int id) {
		//get the user first
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id : "+id);
		
		return user.get().getPosts();		
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		postRepository.deleteById(id);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
		Post savedPost = postRepository.save(post);
		
//This following code will return the location of the user - /users/4 where 4 is the id of the user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
		
}
