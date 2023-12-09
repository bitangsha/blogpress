package com.blogpress.models;

import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
	
	
	private char[] password;
	
	public User(int id, String emailId, String name, char[] password) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.name = name;
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
