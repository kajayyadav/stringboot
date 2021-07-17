package com.a2y.spring.service.three.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer age;
	private Boolean haveAccess;
	public User(String name, Integer age, Boolean haveAccess) {
		this.name = name;
		this.age = age;
		this.haveAccess = haveAccess;
	}
	
	public User() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getHaveAccess() {
		return haveAccess;
	}

	public void setHaveAccess(Boolean haveAccess) {
		this.haveAccess = haveAccess;
	}
	
	

}
