package com.lec.board.service;

import java.sql.Connection;

import com.lec.board.dao.BoardDAO;
import com.lec.db.JDBCUtility;

public class BoardDeleteService {

	private BoardDeleteService() {}
	private static BoardDeleteService boardDeleteService = null;
	public static BoardDeleteService getInstance() {
		if(boardDeleteService == null) boardDeleteService = new BoardDeleteService();
		return boardDeleteService;
	}
	
	public boolean isBoardWriter(int bno, String pass) {
		boolean isWriter = false;
		Connection conn = JDBCUtility.getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		isWriter = boardDAO.isBoardWriter(bno, pass);
		return isWriter;
	}

	public boolean deleteBoard(int bno) {
		
		boolean isDeleteSuccess = false;
		
		Connection conn = JDBCUtility.getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn); 		
		int deleteCount = boardDAO.deleteBoard(bno);
		
		if(deleteCount > 0) {
			JDBCUtility.commit(conn);
			JDBCUtility.close(conn, null, null);
			isDeleteSuccess = true;
		} else {
			JDBCUtility.rollback(conn);
		}
		return isDeleteSuccess;
	}

}
