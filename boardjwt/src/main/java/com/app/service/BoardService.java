package com.app.service;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.app.entity.Board;
import com.app.repository.BoardRepository;

@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public Board getArticle(int articleNo) throws DataAccessException {
		Optional<Board> _board = boardRepository.findById(articleNo);
		Board board= null;
		if(_board.isPresent()) {
			board = _board.get();
		}
		return board;
	}
}
