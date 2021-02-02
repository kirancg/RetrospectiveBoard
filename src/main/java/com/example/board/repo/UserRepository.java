package com.example.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
