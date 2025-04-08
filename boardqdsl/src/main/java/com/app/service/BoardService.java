package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.app.entity.QBoard;
import com.app.entity.QQBoard;
import com.app.repository.BoardRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<QBoard> listArticles() throws DataAccessException {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QQBoard qqboard = QQBoard.qBoard;
		//List<QBoard> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		return queryFactory.selectFrom(qqboard).orderBy(qqboard.writeDate.desc()).fetch();
	}
	
	public void addArticle(QBoard qboard) {
		boardRepository.save(qboard);
	}
	
	public QBoard viewArticle(int articleNo) throws DataAccessException {
		QQBoard qqboard = QQBoard.qBoard;
		Predicate predicate = qqboard.id.eq(articleNo);
		QBoard qboard = null;
		Optional<QBoard> optionalBoard = boardRepository.findOne(predicate);
		if(optionalBoard.isPresent()) {
			qboard = optionalBoard.get();
		}
		return qboard;
	}
	
	public void editArticle(QBoard inQBoard) throws DataAccessException {
		QQBoard qqboard = QQBoard.qBoard;
		Predicate predicate = qqboard.id.eq(inQBoard.getId());
		QBoard qboard = null;
		Optional<QBoard> optionalBoard = boardRepository.findOne(predicate);
		
		if(optionalBoard.isPresent()) {
			qboard = optionalBoard.get();
			qboard.setTitle(inQBoard.getTitle());
			qboard.setContent(inQBoard.getContent());
			boardRepository.save(qboard);
		}
	}
	
	public void removeArticle(int articleNo) throws DataAccessException {
		boardRepository.deleteById(articleNo);
	}
}
