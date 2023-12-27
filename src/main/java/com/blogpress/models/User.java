package com.blogpress.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="user_details")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=3, message="[Email id must be more than 3 characters]")
	@JsonProperty("email_id")
	@Column(name="EMAILID")
	private String emailId;
	
	@Size(min=3, message="[Name must be more than 3 characters]")
	@JsonProperty("user_name")
	@Column(name="username")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")   //user - which field in post entity owns this relationship ?
	private List<Post> posts;
	
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	private char[] password;
	
	
	
	public User() {
		super();
	}

	public User(int id, @Size(min = 3, message = "[Email id must be more than 3 characters]") String emailId,
			@Size(min = 3, message = "[Name must be more than 3 characters]") String name, List<Post> posts,
			char[] password) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.name = name;
		this.posts = posts;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
	
	

}
