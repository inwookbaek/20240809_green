package com.b_project.model.review.service;

import java.sql.Connection;

import com.b_project.model.review.dao.ReviewDAO;
import com.b_project.model.review.model.ReviewBoardBean;
import com.b_project.util.JDBCUtil;

public class ReviewPageService {
	
	public ReviewBoardBean getArticle(int review_no) {
		
		ReviewBoardBean boardBean = null;
		Connection conn = JDBCUtil.getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();

		int updateCount = reviewDAO.updateReadCount(conn, review_no);
		
		if(updateCount > 0) JDBCUtil.commit(conn);
		else JDBCUtil.rollback(conn);
		
		boardBean = reviewDAO.selectArticle(conn, review_no);
		JDBCUtil.close(conn);
		return boardBean;
		
		
	}


	
}
