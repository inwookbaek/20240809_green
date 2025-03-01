package com.lec.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.board.service.BoardReplyService;
import com.lec.board.vo.ActionForward;
import com.lec.board.vo.BoardVO;

public class BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = new ActionForward();
		BoardVO board = new BoardVO();
		
		int p = Integer.parseInt(req.getParameter("p"));
		
		board.setBno(Integer.parseInt(req.getParameter("bno")));
		board.setWriter(req.getParameter("writer"));
		board.setPass(req.getParameter("pass"));
		board.setSubject(req.getParameter("subject"));
		board.setContent(req.getParameter("content"));
		board.setRe_ref(Integer.parseInt(req.getParameter("re_ref")));
		board.setRe_lev(Integer.parseInt(req.getParameter("re_lev")));
		board.setRe_seq(Integer.parseInt(req.getParameter("re_seq")));
		
		BoardReplyService boardReplyService = BoardReplyService.getInstance();
		boolean isReplySuccss = boardReplyService.replyBoard(board);
		String msg = "";
		
		if(isReplySuccss) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.do?p=" + p);			
		} else {
			try {
				msg = "댓글 등록 실패!!!";
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
