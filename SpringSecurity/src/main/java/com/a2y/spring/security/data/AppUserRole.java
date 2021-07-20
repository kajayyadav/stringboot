package com.a2y.spring.security.data;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum AppUserRole {

	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(AppUserPermission.STUDENT_READ,
			AppUserPermission.STUDENT_WRITE,AppUserPermission.COURSE_READ,
			AppUserPermission.COURSE_WRITE)),
	ADMINTRAINEE(Sets.newHashSet(AppUserPermission.STUDENT_READ,
			AppUserPermission.COURSE_READ));

	private final Set<AppUserPermission> permissions;

	private AppUserRole(Set<AppUserPermission> permissions) {
		this.permissions = permissions;
	}
	public Set<GrantedAuthority> getGrantedAuthorities(){
		Set<GrantedAuthority> perms = permissions.stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
		.collect(Collectors.toSet());
		perms.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return perms;
	}
	
}
