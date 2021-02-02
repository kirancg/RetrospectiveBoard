package com.example.board.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.model.Comment;
import com.example.board.repo.CommentRepository;

@Service
@Transactional(readOnly = true)
public class CommentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
	
	@Autowired
	private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	//saveAll 
	@Transactional
	public List<Comment> saveAll(List<Comment> comments){
		LOGGER.info("Saving{}", comments);
		return commentRepository.saveAll(comments);
	}
	
	//getAllComments For Today
	public List<Comment> getAllCommentsForToday() {
		LocalDate localDate = LocalDate.now();
		return commentRepository.findByCreatedYearAndMonthAndDay(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
	}
}
