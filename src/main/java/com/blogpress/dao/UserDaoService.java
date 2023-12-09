package com.blogpress.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.blogpress.models.User;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1, "abc@gmail.com", "John", null));
		users.add(new User(2, "def@outlook.com", "Richard", null));
		users.add(new User(3, "ghi@protonmail.com", "Darla", null));
	}
	
	private int usersCount=users.size();
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId()==id;
		return users.stream().filter(predicate).findFirst().get();
		
		
		/*
		 * same code as above, but without using fp
		 * 
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).getId()==id)
				return users.get(i);
		}
		return null;
		*/
		
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId()==id;
		users.removeIf(predicate);		
	}

	public User addUser(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
}
