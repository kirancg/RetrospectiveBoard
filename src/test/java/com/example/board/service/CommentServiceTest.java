package com.example.board.service;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.board.model.Comment;
import com.example.board.model.CommentType;
import com.example.board.repo.CommentRepository;

@RunWith(SpringRunner.class)
public class CommentServiceTest {
	
	@MockBean 
	private CommentRepository commentRepository;
	
	private CommentService commentService;
	
	@Before
	public void init() {
		commentService = new CommentService(commentRepository);
	}
	
	@Test
	public void getAllCommentsForToday_HappyPath_ShouldReturn1Comment() {
		 // Given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        List<Comment> comments = Arrays.asList(comment);
        LocalDate now = LocalDate.now();
        when(commentRepository.findByCreatedYearAndMonthAndDay(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth())).thenReturn(comments);

        // When
        List<Comment> actualComments = commentService.getAllCommentsForToday();

        // Then
        verify(commentRepository, times(1)).findByCreatedYearAndMonthAndDay(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth());
        assertThat(comments).isEqualTo(actualComments);
	}
	
	@Test
	public void saveAll_HappyPath_ShouldSave2Comments() {
	
		 Comment comment = new Comment();
	        comment.setComment("Test Plus");
	        comment.setType(CommentType.PLUS);
	        comment.setCreatedBy("Shazin");
	        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));

	        Comment comment2 = new Comment();
	        comment2.setComment("Test Star");
	        comment2.setType(CommentType.STAR);
	        comment2.setCreatedBy("Shahim");
	        comment2.setCreatedDate(new Timestamp(System.currentTimeMillis()));
	        List<Comment> comments = Arrays.asList(comment,comment2);
	        when(commentRepository.saveAll(comments)).thenReturn(comments);

	        // When
	        List<Comment> saved = commentService.saveAll(comments);

	        // Then
	        assertThat(saved).isNotEmpty();
	        verify(commentRepository, times(1)).saveAll(comments);
		
	}
	
}
