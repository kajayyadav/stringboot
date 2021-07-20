package com.a2y.spring.security.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.a2y.spring.security.data.AppUserRole;
import com.a2y.spring.security.model.AppUser;
import com.google.common.collect.Lists;

@Repository
public class AppUserRepositoryImpl implements AppUserRepository {

	private PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Override
	public Optional<AppUser> findAppUserByUsername(String username) {
		return getAppUsers().stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	private List<AppUser> getAppUsers() {
		List<AppUser> applicationUsers = Lists.newArrayList(new AppUser(

				AppUserRole.STUDENT.getGrantedAuthorities().stream().collect(Collectors.toList()), "ay",
				getPasswordEncoder().encode("one234"), true, true, true, true),
				new AppUser(

						AppUserRole.ADMIN.getGrantedAuthorities().stream().collect(Collectors.toList()), "aj",
						getPasswordEncoder().encode("1two34"), true, true, true, true),
				new AppUser(

						AppUserRole.ADMINTRAINEE.getGrantedAuthorities().stream().collect(Collectors.toList()), "sy",
						getPasswordEncoder().encode("12three4"), true, true, true, true));

		return applicationUsers;
	}
}
