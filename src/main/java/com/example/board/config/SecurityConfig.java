package com.example.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.board.model.User;
import com.example.board.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private UserService userDetailService;
	
	@Override
	 public void configure(WebSecurity web) throws Exception {
	 web.ignoring().antMatchers("/h2-console/**");
	 }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.and()
			.logout()
			.permitAll()
			.and()
			.authorizeRequests()
				.antMatchers("/**")
				.hasRole("USER");	
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new
				DaoAuthenticationProvider();
				 authenticationProvider.setPasswordEncoder(passwordEncoder());
				 authenticationProvider.setUserDetailsService(userDetailService);
				//authenticationProvider.setUserDetailService(userDetailService);
				 return authenticationProvider;

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	 public ApplicationRunner applicationRunner() {
	 return args -> {
		 userDetailService.create(new User(null, "kiran",
	passwordEncoder().encode("password"),
	 "ROLE_USER"));
		 userDetailService.create(new User(null, "kumar",
	passwordEncoder().encode("password"),
	 "ROLE_USER"));
	 };
	}
} 
