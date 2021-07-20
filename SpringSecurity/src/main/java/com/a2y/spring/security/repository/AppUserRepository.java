package com.a2y.spring.security.repository;

import java.util.Optional;

import com.a2y.spring.security.model.AppUser;

public interface AppUserRepository {
	
	Optional<AppUser> findAppUserByUsername(String username);

}
