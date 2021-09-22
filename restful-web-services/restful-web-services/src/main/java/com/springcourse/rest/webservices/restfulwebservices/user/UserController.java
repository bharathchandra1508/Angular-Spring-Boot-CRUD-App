/**
 * 
 */
package com.springcourse.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author 001ZAV744
 *
 */

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService service;
	
	@RequestMapping(method = RequestMethod.GET, path = "/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id - " + id);
		}
		return user;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id - " + id);
		}
		return user;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/users",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
