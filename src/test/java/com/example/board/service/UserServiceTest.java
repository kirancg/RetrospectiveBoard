package com.example.board.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.board.model.User;
import com.example.board.repo.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	@MockBean
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void init() {
        this.userService = new UserService(userRepository);
    }
    @Test
    public void getAllCommentsForToday_HappyPath_ShouldReturn1Comment() {
    	User user = new User();
    	user.setUsername("kiran");
    	user.setPassword("kiran007");
    	user.setRole("USER");
    	
    	when(userRepository.findByUsername("kiran")).thenReturn(user);
    	
    	UserDetails actual = userService.loadUserByUsername("shazin");
    	
    	// Then
        verify(userRepository, times(1)).findByUsername("shazin");
    }
}
