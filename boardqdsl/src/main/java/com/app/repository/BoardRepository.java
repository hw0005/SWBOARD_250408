package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.app.entity.QBoard;

public interface BoardRepository extends JpaRepository<QBoard, Integer>, QuerydslPredicateExecutor<QBoard> {

}
