package com.a2y.spring.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTTokenVerifierFilter extends OncePerRequestFilter {

	String key = "this is my private key for token";
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = null;
		try {
			token = authorizationHeader.replace("Bearer ", "");
			Jws<Claims> jwsClaims =  
			Jwts
			.parser()
			.setSigningKey(key.getBytes())
			.parseClaimsJws(token);
			
			Claims body = jwsClaims.getBody();
			String username = body.getSubject();
			Set<SimpleGrantedAuthority>  authorities= ((List<Map<Strings,String>>) body.get("authorites"))
					.stream().map(m -> new SimpleGrantedAuthority(m.get("authority")))
					.collect(Collectors.toSet());
			Authentication authentication = new UsernamePasswordAuthenticationToken
					(username,null, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		} catch (JwtException e) {
			throw new IllegalStateException(String.format("Token %S cant not be trusted",token));
		}
		filterChain.doFilter(request, response);
	}

}
