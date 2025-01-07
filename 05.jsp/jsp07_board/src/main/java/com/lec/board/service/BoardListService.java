package com.lec.board.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.lec.board.dao.BoardDAO;
import com.lec.board.vo.BoardVO;
import com.lec.db.JDBCUtility;

public class BoardListService {

	private  BoardListService() {}
	private static BoardListService boardListService = null;
	public static BoardListService getInstance() {
		if(boardListService == null) boardListService = new BoardListService();
		return boardListService;
	}
	public int getListCount(String f, String q) {
		// 글전체갯수 : select count(*) from board where subject like '%%';		
		int listCount = 0;
		Connection conn = JDBCUtility.getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();		
		boardDAO.setConnection(conn);		
		listCount = boardDAO.selectListCount(f, q);	
		return listCount;
	}
	
	public List<BoardVO> getBoardList(int p, int l, String f, String q) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		Connection conn = JDBCUtility.getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();		
		boardDAO.setConnection(conn);
		boardList = boardDAO.selectBoardList(p, l, f, q);	
		return boardList;
	}
}
