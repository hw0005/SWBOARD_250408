package com.app.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("boardDao")
public interface BoardDao {
	public List<NoticeBoard> selectAllArticles() throws DataAccessException;
	
	public void insertArticle(NoticeBoard noticeBoard) throws DataAccessException;
	
	public NoticeBoard selectArticle (int articleNo) throws DataAccessException;
	
	public void updateArticle (NoticeBoard noticeBoard) throws DataAccessException;
	
	public void deleteArticle (int article) throws DataAccessException;
	
	
}
