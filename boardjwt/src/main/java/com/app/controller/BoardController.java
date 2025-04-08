package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Board;
import com.app.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/get/{num}")
	public Board getArticle(@PathVariable("num") int articleNo) throws Exception {
		Board board = boardService.getArticle(articleNo);
		return board;
	}
	
}
