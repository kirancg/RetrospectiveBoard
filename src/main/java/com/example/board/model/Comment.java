package com.example.board.model;

import javax.persistence.*;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity //mark as JPA entity
@Table(name="rb_comment") // table name to which comment class needs to be mapped
@EntityListeners(AuditingEntityListener.class) 
/* The @EntityListeners annotation is used with
the AuditingEntityListener implementation to dynamically populate
the createdDate and createdBy properties annotated with @CreatedDate and
@CreatedBy in the Comment domain model when persisting the comment entry into the
table.*/
@Data
/* 
This means getters, setters, the equals method, the hashCode
method, and the toString method will be generated for that class.
 */
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String comment;
	
	@Enumerated(EnumType.STRING)
	/*that the value of the enum CommentType needs to be persisted as a String type in the database. */
	private CommentType type;
	
	@CreatedDate
	private Timestamp createdDate;
	
	@CreatedBy
	private String createdBy;
}
