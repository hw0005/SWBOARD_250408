package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.BoardUser;

public interface BoardUserRepository extends JpaRepository <BoardUser, Long>{
	BoardUser getByUid(String uid);
}
