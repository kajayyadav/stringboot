package com.a2y.spring.service.three.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a2y.spring.service.three.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByName(String name);
}
