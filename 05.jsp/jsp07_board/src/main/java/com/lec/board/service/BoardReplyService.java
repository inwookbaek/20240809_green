package com.lec.board.service;

import java.sql.Connection;

import com.lec.board.dao.BoardDAO;
import com.lec.board.vo.BoardVO;
import com.lec.db.JDBCUtility;

public class BoardReplyService {

	private BoardReplyService() {}
	private static BoardReplyService boardReplyService = null;
	public static BoardReplyService getInstance() {
		if(boardReplyService == null) boardReplyService = new BoardReplyService();
		return boardReplyService;
	}
	
	public boolean replyBoard(BoardVO board) {
		
		boolean isReplySuccss = false;
		Connection conn = JDBCUtility.getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		int insertCount = boardDAO.insertReplyBoard(board);
		
		if(insertCount > 0) {
			JDBCUtility.commit(conn);
			JDBCUtility.close(conn, null, null);
			isReplySuccss = true;
		} else {
			JDBCUtility.rollback(conn);
		}		
		return isReplySuccss;
	}
}
