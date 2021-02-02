package com.example.board.service;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.model.User;
import com.example.board.repo.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//get Username
	/*
	public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
			User user = userRepository.findByUsername(username);
			
			if(user == null) {
				throw new UsernameNotFoundException(username);
			}
			
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
	}
	*/
	
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUsername(username);

	        if(user == null) {
	            throw new UsernameNotFoundException(username);
	        }

	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
	    }
	
	//Save the user
	@Transactional(rollbackFor = Exception.class)
	public User create(User user) {
		//return userRepository.save(user);
		return userRepository.save(user);
	}
}
