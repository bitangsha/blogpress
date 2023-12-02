package com.blogpress.models;

public class User {
	
	private int id;
	
	private String emailId;
	
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
