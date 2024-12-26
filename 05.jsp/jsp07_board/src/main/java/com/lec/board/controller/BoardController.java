package com.lec.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.board.action.Action;
import com.lec.board.action.BoardDeleteAction;
import com.lec.board.action.BoardDeleteFormAction;
import com.lec.board.action.BoardDetailAction;
import com.lec.board.action.BoardListAction;
import com.lec.board.action.BoardModifyAction;
import com.lec.board.action.BoardModifyFormAction;
import com.lec.board.action.BoardReplyAction;
import com.lec.board.action.BoardReplyFormAction;
import com.lec.board.action.BoardWriteAction;
import com.lec.board.vo.ActionForward;

@WebServlet("*.do")
public class BoardController extends HttpServlet {

	Action action = null;
	ActionForward forward = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		process(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		process(req, res);
	}

	private void process(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath(); 
		String command = requestURI.substring(contextPath.length()+1, requestURI.length()-3); // *.do
		
		RequestDispatcher dispatcher = null;

		// 목록조회/글쓰기/글수정/글삭제/댓글/에러/다운
		if(command.equalsIgnoreCase("boardWriteForm")) {
			forward = new ActionForward();
			forward.setPath("/board/board_write.jsp");
		} else if(command.equalsIgnoreCase("boardWrite")) {
			action = new BoardWriteAction();
			forward = action.execute(req, res);
		} else if(command.equalsIgnoreCase("boardList")) {
			action = new BoardListAction();
			forward = action.execute(req, res);
		} else if(command.equalsIgnoreCase("boardDetail")) {
			action = new BoardDetailAction();
			forward = action.execute(req, res);
		} else if(command.equalsIgnoreCase("boardModifyForm")) {
			action = new BoardModifyFormAction();
			forward = action.execute(req, res);
		} else if(command.equalsIgnoreCase("boardModify")) {
			action = new BoardModifyAction();
			forward = action.execute(req, res);
		} else if(command.equalsIgnoreCase("boardDeleteForm")) {
			action = new BoardDeleteFormAction();
			forward = action.execute(req, res);
		} else if(command.equalsIgnoreCase("boardDelete")) {
			action = new BoardDeleteAction();
			forward = action.execute(req, res);
		} else if(command.equalsIgnoreCase("boardReplyForm")) {
			action = new BoardReplyFormAction();
			forward = action.execute(req, res);
		} else if(command.equalsIgnoreCase("boardReply")) {
			action = new BoardReplyAction();
			forward = action.execute(req, res);
		} else if(command.equalsIgnoreCase("download")) {
			forward = new ActionForward();
			forward.setPath("/board/board_download.jsp");
		} else if(command.equalsIgnoreCase("error")) {
			forward = new ActionForward();
			forward.setPath("/board/error.jsp");
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				res.sendRedirect(forward.getPath());
			} else {
				dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, res);
			}
		}
	}	
}
