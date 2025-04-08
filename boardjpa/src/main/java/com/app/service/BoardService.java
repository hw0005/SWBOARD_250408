package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.entity.Board;
import com.app.repository.BoardRepository;

@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<Board> listArticles() throws DataAccessException {
		List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		return boardList;
	}
	
	public void addArticle(Board board) {
		boardRepository.save(board);
	}
	
	public Board viewArticle(int articleNo) throws DataAccessException {
		Optional<Board> optionalBoard = boardRepository.findById(articleNo);
		Board board = null;
		if(optionalBoard.isPresent()) {
			board = optionalBoard.get();
		}
		return board;
	}
	
	public void editArticle(Board inBoard) throws DataAccessException {
		Optional<Board> optionalBoard = boardRepository.findById(inBoard.getId());
		Board board = null;
		if(optionalBoard.isPresent()) {
			board = optionalBoard.get();
			board.setTitle(inBoard.getTitle());
			board.setContent(inBoard.getContent());
			boardRepository.save(board);
		}
	}
	
	public void removeArticle(int articleNo) throws DataAccessException {
		boardRepository.deleteById(articleNo);
	}
	
}
