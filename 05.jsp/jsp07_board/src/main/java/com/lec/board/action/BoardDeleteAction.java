package com.lec.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.board.service.BoardDeleteService;
import com.lec.board.vo.ActionForward;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		
		ActionForward forward = null;
		boolean isWriter = false;
		boolean isDeleteSuccess = false;
		
		int p = Integer.parseInt(req.getParameter("p"));
		int bno = Integer.parseInt(req.getParameter("bno"));
		
		BoardDeleteService boardDeleteService = BoardDeleteService.getInstance();
		isWriter = boardDeleteService.isBoardWriter(bno, req.getParameter("pass"));
		String msg = "";
		
		if(isWriter) {
			isDeleteSuccess = boardDeleteService.deleteBoard(bno);
			
			if(isDeleteSuccess) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath(String.format("boardList.do?p=%d", p));				
			} else {
				try {
					msg = "게시글 삭제 실패!!!";
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("  alert('" + msg + "')");
					out.println("  history.back()");
					out.println("</script>");					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				msg = "게시글을 삭제할 권한이 없습니다!!!";
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('" + msg + "')");
				out.println("  history.back()");
				out.println("</script>");					
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return forward;
	}
}
