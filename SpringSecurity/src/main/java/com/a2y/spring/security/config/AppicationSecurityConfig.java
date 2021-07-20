package com.a2y.spring.security.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.a2y.spring.security.data.AppUserRole;
import com.a2y.spring.security.jwt.JWTTokenVerifierFilter;
import com.a2y.spring.security.jwt.JWTUsernameAndPasswordAuthenticationFilter;
import com.a2y.spring.security.service.AppUserService;

@EnableGlobalMethodSecurity(prePostEnabled = true)

@Configuration
@EnableWebSecurity
public class AppicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Autowired
	private AppUserService appUserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(new JWTUsernameAndPasswordAuthenticationFilter(authenticationManager()))
				.addFilterAfter(new JWTTokenVerifierFilter(), JWTUsernameAndPasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*").permitAll()
				.antMatchers("/api/**").hasRole(AppUserRole.STUDENT.name())
				
				.anyRequest()
					.authenticated()
					;
				/*.and()
				.formLogin()
				.loginPage("/login")
					.permitAll()
					.defaultSuccessUrl("/cources", true)
					.usernameParameter("username")
					.passwordParameter("password")

				.and()
				.rememberMe()
				.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
					.rememberMeParameter("remember-me")
					.key("somethingverysecure")
				.and()
				.logout()
					.logoutUrl("/logout")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
					.clearAuthentication(true)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID", "remember-me")
					.logoutSuccessUrl("/login");*/
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(getPasswordEncoder());
		provider.setUserDetailsService(appUserService);
		return provider;
		
	}
	
	/*
	 * @Override
	 * 
	 * @Bean protected UserDetailsService userDetailsService() {
	 * 
	 * return new InMemoryUserDetailsManager(
	 * User.builder().username("ay").password(getPasswordEncoder().encode("one234"))
	 * // .roles(AppUserRole.STUDENT.name())
	 * .authorities(AppUserRole.STUDENT.getGrantedAuthorities()).build(),
	 * User.builder().username("aj").password(getPasswordEncoder().encode("1two34"))
	 * // .roles(AppUserRole.ADMIN.name())
	 * .authorities(AppUserRole.ADMIN.getGrantedAuthorities()).build(),
	 * User.builder().username("sy").password(getPasswordEncoder().encode("12three4"
	 * )) // .roles(AppUserRole.ADMINTRAINEE.name())
	 * .authorities(AppUserRole.ADMINTRAINEE.getGrantedAuthorities()).build()
	 * 
	 * ); }
	 */
}
