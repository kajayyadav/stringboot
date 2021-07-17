package com.a2y.spring.service.three.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a2y.spring.service.three.model.User;
import com.a2y.spring.service.three.service.BusinessService;

@RestController
public class ThreeController {
	
	@Autowired
	BusinessService service;

	@RequestMapping(value ="/user", method =RequestMethod.GET)
	public List<User> getAllUsers(){
		
		return service.getAllUsers();
		
	}
	
	@RequestMapping(value ="/user", method =RequestMethod.POST)
	public User addUser(@RequestBody User user){
		
		return service.saveUser(user);
		
	}
	
	@RequestMapping(value ="/user/byName", method =RequestMethod.GET)
	public User getUserByName(@RequestParam String name){
		
		return service.getUserByName(name);
		
	}
}
