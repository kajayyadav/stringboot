package com.a2y.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.a2y.spring.security.repository.AppUserRepository;



@Service
public class AppUserService implements UserDetailsService{

	@Autowired
	AppUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findAppUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username+" not found"))
				;
	}

}
