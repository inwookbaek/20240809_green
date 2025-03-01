package com.lec.board.action;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.board.service.BoardModifyService;
import com.lec.board.vo.ActionForward;
import com.lec.board.vo.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {

		ActionForward forward = null;
		BoardVO board = null;
		String realFolder = null;
		String saveFolder = "D:/lec/00.share/98.temp/upload";
		int fileSize = 1024*1024*5; // 5mb
		
		ServletContext context = req.getServletContext();
		boolean isWriter = false;
		boolean isModifySuccess = false;
		
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(req, saveFolder
					, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			int p = Integer.parseInt(multi.getParameter("p"));
			int bno = Integer.parseInt(multi.getParameter("bno"));
			String pass = multi.getParameter("pass");
			
			board = new BoardVO();
			BoardModifyService boardModifyService = BoardModifyService.getInstance();
			isWriter = boardModifyService.isBoardWriter(bno, pass);
			String msg = "";
		
			if(isWriter) {
				board.setBno(bno);
				board.setSubject(multi.getParameter("subject"));
				board.setContent(multi.getParameter("content"));
				board.setFile(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
				isModifySuccess = boardModifyService.modifyBoard(board);
				
				if(isModifySuccess) {
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath(String.format("boardDetail.do?p=%d&bno=%d", p, bno));					
				} else {
					msg = "게시글 수정 실패!!!";
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("  alert('" + msg + "')");
					out.println("  history.back()");
					out.println("</script>");
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("error.do?msg=" + URLEncoder.encode(msg, "utf-8"));
				}
			} else {
				msg = "게시글을 수정할 권한이 없습니다!\n비밀번호를 확인하세요!!";
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('" + msg + "')");
				out.println("  history.back()");
				out.println("</script>");	
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("error.do?msg=" + URLEncoder.encode(msg, "utf-8"));					
			}		
			
		} catch (Exception e) {
			System.out.println("게시글수정실패!!!" + e.getMessage());
		} 
	
		return forward;
	}
}
