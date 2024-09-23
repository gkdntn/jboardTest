package com.jboard.controller.article;

import java.io.IOException;

import com.jboard.dto.FileDTO;
import com.jboard.service.FileService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/fileDownload.do")
public class FileDownloadController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private FileService service = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fno = req.getParameter("fno");
		// 파일 조회
		FileDTO fileDto = service.selectFile(fno);
		
		req.setAttribute("fileDto", fileDto);
		
		service.updateFileDownloadCount(fno);
		
		service.fileDownload(req, resp);
		
	}

}
