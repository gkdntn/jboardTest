package com.jboard.service;


import com.jboard.dao.TermsDAO;
import com.jboard.dto.TermsDTO;


public class TermsService {

	private static TermsService instance = new TermsService();
	public static TermsService getInstance() {
		return instance;
	}
	
	private TermsService () {}
	
	private TermsDAO dao = TermsDAO.getInstance();
	
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
	
}
