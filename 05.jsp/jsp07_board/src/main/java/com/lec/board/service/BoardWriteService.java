package com.lec.board.service;

import java.sql.Connection;

import com.lec.board.dao.BoardDAO;
import com.lec.board.vo.BoardVO;
import com.lec.db.JDBCUtility;

public class BoardWriteService {

	private BoardWriteService() {}
	private static BoardWriteService boardWriteService = null;
	public static BoardWriteService getInstance() {
		if(boardWriteService == null) boardWriteService = new BoardWriteService();
		return boardWriteService;
	}
	
	public boolean registerBoard(BoardVO board) {
		
		boolean isWriteSuccess = false;
			
		Connection conn = JDBCUtility.getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		int insertCount = boardDAO.insert(board);
		
		if(insertCount > 0) {
			JDBCUtility.commit(conn);
			JDBCUtility.close(conn, null, null);
			isWriteSuccess = true;
		} else {
			JDBCUtility.rollback(conn);
		}
		return isWriteSuccess;
	}
}
