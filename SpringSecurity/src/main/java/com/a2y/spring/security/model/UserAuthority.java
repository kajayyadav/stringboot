package com.a2y.spring.security.model;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority{
	private String authority;
	private String username;
	
	public UserAuthority() {
	}

	public UserAuthority(String authority, String username) {
		this.authority = authority;
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	
	
}
