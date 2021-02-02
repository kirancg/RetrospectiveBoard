package com.example.board.repo;

//import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.board.model.Comment;
import com.example.board.model.CommentType;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepoTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Test
	public void findByCreatedYearAndMonthAndDay_HappyPath_ShouldReturn1Comment() {
		
		//Create a comment object
		Comment comment = new Comment();
		comment.setComment("Test");
		comment.setType(CommentType.PLUS);
		comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
		//persist it to the temporary database using entityManager
		testEntityManager.persist(comment);
		testEntityManager.flush();
		
		//retrieve the value from database
		LocalDate now = LocalDate.now();
		List<Comment> comments = commentRepository.findByCreatedYearAndMonthAndDay(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
		
		//check the values
		assertThat(comments).hasSize(1);
		assertThat(comments.get(0)).hasFieldOrPropertyWithValue("comment", "Test");
		
	}
	@Test
	public void  save_HappyPath_ShouldSave1Comment() {
		//
		Comment comment = new Comment();
		comment.setComment("Test");
		comment.setType(CommentType.PLUS);
		comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
		Comment saved = commentRepository.save(comment);
		
		assertThat(testEntityManager.find(Comment.class,saved.getId())).isEqualTo(saved);
	}
}
