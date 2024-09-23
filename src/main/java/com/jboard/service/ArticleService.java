package com.jboard.service;

import java.util.List;

import com.jboard.dao.ArticleDAO;
import com.jboard.dto.ArticleDTO;
import com.jboard.dto.PageGroupDTO;

public enum ArticleService {
	INSTANCE;
	
	private ArticleDAO dao = ArticleDAO.getInstance();
	
	public int getLastPageNum(int total) {
		
		int lastPageNum;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
	
	// 페이지 시작번호(limit용)
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	// 현재 페이지번호 구하기
	public int getCurrentPage(String pg) {
		
		int currentPage = 1;
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		
		return currentPage;
	}
	
	// 현재 페이지 그룹 구하기
	public PageGroupDTO getCurrentPageGroup(int currentPage) {
		
		int currentPageGroup = (int) Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		return new PageGroupDTO(pageGroupStart, pageGroupEnd);
	}
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	
	public int selectCountTotal() {
		return dao.selectCountTotal();
	}
	
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDTO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
	
}
