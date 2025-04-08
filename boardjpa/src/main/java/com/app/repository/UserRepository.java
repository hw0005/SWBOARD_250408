package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.BoardUser;

public interface UserRepository extends JpaRepository<BoardUser, Long>{
	Optional<BoardUser> findByUsername (String username);
}
