/**
 * 
 */
package com.springcourse.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author 001ZAV744
 *
 */

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int userCount = 4;
	
	static {
		users.add(new User(1,"Bharath",new Date()));
		users.add(new User(2,"Jayanth",new Date()));
		users.add(new User(3,"Ajay",new Date()));
		users.add(new User(4,"Shubham",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getId() == id) {
				User user = users.remove(i);
				return user;
			}
		}
		return null;
	}
	
}
