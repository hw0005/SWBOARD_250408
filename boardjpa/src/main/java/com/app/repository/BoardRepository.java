package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
