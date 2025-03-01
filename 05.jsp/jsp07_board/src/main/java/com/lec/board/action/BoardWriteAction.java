package com.lec.board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.board.service.BoardWriteService;
import com.lec.board.vo.ActionForward;
import com.lec.board.vo.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		
		// System.out.println("1. 게시글등록하기");
		ActionForward forward = null;
		BoardVO board = null;
		String realFolder = null;
		String saveFolder = "D:/lec/00.share/98.temp/upload";
		int fileSize = 1024*1024*5; // 5mb
		
		ServletContext context = req.getServletContext();
		realFolder = context.getRealPath("upload");
		
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(req, saveFolder
					, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			board = new BoardVO();
			board.setWriter(multi.getParameter("writer"));
			board.setPass(multi.getParameter("pass"));
			board.setSubject(multi.getParameter("subject"));
			board.setContent(multi.getParameter("content"));
			board.setFile(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
			
			// 첨부파일이 여러개일 경우
			// Enumeration files = multi.getFileNames();
			// String fn = "";
			// if(files.hasMoreElements()) {
		    //		fn = (String) files.nextElement();
			//	System.out.println(multi.getOriginalFileName(fn));
			// }
			
			BoardWriteService boardWriteService = BoardWriteService.getInstance();
			boolean isWriteSuccess = boardWriteService.registerBoard(board);
			
			if(isWriteSuccess) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardList.do");
			} else {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('게시글등록이 실패 했습니다!!')");
				out.println("  history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;				
	}
}
