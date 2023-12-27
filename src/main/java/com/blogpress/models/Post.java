package com.blogpress.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="post")
public class Post {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
		
	
	
	public Post() {
		super();
	}

	public Post(int id, String content, User user) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
