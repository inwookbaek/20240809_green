package com.lec.board.service;

import java.sql.Connection;

import com.lec.board.dao.BoardDAO;
import com.lec.board.vo.BoardVO;
import com.lec.db.JDBCUtility;

public class BoardDetailService {

	private BoardDetailService() {}
	private static BoardDetailService boardDetailService = null;
	public static BoardDetailService getInstance() {
		if(boardDetailService == null) boardDetailService = new BoardDetailService();
		return boardDetailService;
	}

	public BoardVO getBoard(int bno) {
		BoardVO board = null;
		
		Connection conn = JDBCUtility.getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		int readCount = boardDAO.updateReadCount(bno);
		if(readCount > 0) JDBCUtility.commit(conn); else JDBCUtility.rollback(conn);
		board = boardDAO.selectBoard(bno);
		return board;
	}
}
