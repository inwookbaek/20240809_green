package com.lec.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.board.service.BoardDetailService;
import com.lec.board.vo.ActionForward;
import com.lec.board.vo.BoardVO;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = new ActionForward();
		
		int p = Integer.parseInt(req.getParameter("p"));
		int bno = Integer.parseInt(req.getParameter("bno"));
		
		BoardDetailService boardDetailService = BoardDetailService.getInstance();
		BoardVO board = boardDetailService.getBoard(bno);		
		
		req.setAttribute("board", board);
		forward.setPath(String.format("/board/board_reply.jsp?p=%d&bno=%d", p, bno));
		return forward;
	}

}
