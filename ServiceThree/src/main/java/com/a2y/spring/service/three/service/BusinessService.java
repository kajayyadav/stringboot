package com.a2y.spring.service.three.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2y.spring.service.three.model.User;
import com.a2y.spring.service.three.repository.UserRepository;

@Service
public class BusinessService {

	@Autowired
	UserRepository repository;
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	public User saveUser(User user){
		return repository.save(user);
	}
	
	public User getUserByName(String userName){
		return repository.findByName(userName);
	}
}
