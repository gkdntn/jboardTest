package com.jboard.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dto.ArticleDTO;
import com.jboard.dto.FileDTO;
import util.DBHelper;
import util.SQL;

public class ArticleDAO extends DBHelper{

	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleDTO dto) {
		
		int no = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate();
			
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			conn.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			closeAll();
		}
		return no;
	}
	
	public int selectCountTotal() {
		
		int total = 0;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_COUNT_TOTAL);
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		
		return total;
	}
	
	public ArticleDTO selectArticle(String no) {
		
		ArticleDTO dto = null;
		List<FileDTO> files = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			// 
			while(rs.next()) {
				if(dto == null) {
					dto = new ArticleDTO();
					dto.setNo(rs.getInt(1));
					dto.setCate(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setComment(rs.getInt(5));
					dto.setFile(rs.getInt(6));
					dto.setHit(rs.getInt(7));
					dto.setWriter(rs.getString(8));
					dto.setRegip(rs.getString(9));
					dto.setRdate(rs.getString(10));
				}
				
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(11));
				fileDto.setAno(rs.getInt(12));
				fileDto.setoName(rs.getString(13));
				fileDto.setsName(rs.getString(14));
				fileDto.setDownload(rs.getInt(15));
				fileDto.setRdate(rs.getString(16));
				files.add(fileDto);
			}
			dto.setFiles(files);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		return dto;
	}
	
	public List<ArticleDTO> selectArticles(int start) {
		
		List<ArticleDTO> articles = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setRow(rs.getInt(1));
				dto.setNo(rs.getInt(2));
				dto.setCate(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setComment(rs.getInt(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdateSubString(rs.getString(11));
				dto.setNick(rs.getString(12));
				
				articles.add(dto);
			}
		
		
	} catch(Exception e){
		logger.error(e.getMessage());
	}finally {
		closeAll();
	}
		return articles;
	}
	public void updateArticle(ArticleDTO dto) {
		
	}
	
	public void deleteArticle(int no) {
		
	}
	
}
