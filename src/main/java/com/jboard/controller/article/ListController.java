package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import com.jboard.dto.ArticleDTO;
import com.jboard.dto.PageGroupDTO;
import com.jboard.service.ArticleService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pg = req.getParameter("pg");

		// 현재 페이지 번호 구하기
		int currentPage = service.getCurrentPage(pg);
		
		// 현재 페이지 그룹 구하기
		PageGroupDTO pageGroup = service.getCurrentPageGroup(currentPage);
		
		// 전체 게시물 갯수 구하기
		int total = service.selectCountTotal();
		
		// 마지막 페이지 번호 구하기
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 시작 번호 구하기
		int start = service.getStartNum(currentPage);
		
		// 데이터 조회
		List<ArticleDTO> articleDto = service.selectArticles(start);
		
		if(lastPageNum <= 10 || pageGroup.getEnd() > lastPageNum) {
			pageGroup.setEnd(lastPageNum);
		}
		
		
		// 공유 참조
		req.setAttribute("articleDto", articleDto);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroup", pageGroup);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/list.jsp");
		dispatcher.forward(req, resp);
		
	}

}
