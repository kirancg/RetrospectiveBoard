package com.example.board.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.board.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	 @Autowired
     private TestEntityManager testEntityManager;

     @Autowired
     private UserRepository userRepository;
     
     @Test
     public void  findByUsername_HappyPath_ShouldReturn1User() {
    	 User user = new User();
    	 user.setUsername("kiran");
    	 user.setPassword("kiran007");
    	 user.setRole("USER");
    	 testEntityManager.persist(user);
    	 testEntityManager.flush();
    	 
    	 User actual = userRepository.findByUsername("kiran");
    	 
    	 assertThat(actual).isEqualTo(user);
     }
     
     @Test
     public void save_HappyPath_ShouldSave1User() throws Exception {
    	 User user = new User();
    	 user.setUsername("kiran");
    	 user.setPassword("kiran007");
    	 user.setRole("USER");
    	 
    	 User actual = userRepository.save(user);
    	 assertThat(actual).isNotNull();
    	 assertThat(actual.getId()).isNotNull();
     }
}
