package com.lec.board.service;

import java.sql.Connection;

import com.lec.board.dao.BoardDAO;
import com.lec.board.vo.BoardVO;
import com.lec.db.JDBCUtility;

public class BoardModifyService {

	private BoardModifyService() {}
	private static BoardModifyService boardModifyService = null;
	public static BoardModifyService getInstance() {
		if(boardModifyService == null) boardModifyService = new BoardModifyService();
		return boardModifyService;
	}
	public boolean isBoardWriter(int bno, String pass) {
		
		boolean isWriter = false;
		Connection conn = JDBCUtility.getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		isWriter = boardDAO.isBoardWriter(bno, pass);
		return isWriter;
	}
	public boolean modifyBoard(BoardVO board) {
		boolean isModifySuccess = false;
		
		Connection conn = JDBCUtility.getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);  	
		int updateCount = boardDAO.updateBoard(board);
		
		if(updateCount > 0) {
			JDBCUtility.commit(conn);
			JDBCUtility.close(conn, null, null);
			isModifySuccess = true;
		} else {
			JDBCUtility.rollback(conn);
		}
			
		return isModifySuccess;
	}
}
