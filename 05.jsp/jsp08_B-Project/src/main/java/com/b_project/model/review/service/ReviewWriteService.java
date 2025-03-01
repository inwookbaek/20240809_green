package com.b_project.model.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.b_project.model.review.dao.ReviewDAO;
import com.b_project.model.review.model.ReviewBoardBean;
import com.b_project.util.JDBCUtil;

public class ReviewWriteService {

	private ReviewDAO reviewDAO = ReviewDAO.getInstance();
	
	public boolean registerReview(ReviewBoardBean item) {

		Connection conn = JDBCUtil.getConnection();
		boolean isWriteSuccess = false;
		int insertCount = reviewDAO.insertReview(conn, item);
		
		if(insertCount > 0) {
			JDBCUtil.commit(conn);
			isWriteSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);		
		return isWriteSuccess;
	}
}
