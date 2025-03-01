package com.b_project.model.review.service;

import java.sql.Connection;

import com.b_project.model.review.dao.ReviewDAO;
import com.b_project.model.review.model.ReviewBoardBean;
import com.b_project.util.JDBCUtil;

public class ReviewModifyService {

	private ReviewDAO reviewDAO = ReviewDAO.getInstance();
	
	public boolean modifyReview(ReviewBoardBean item) {

		Connection conn = JDBCUtil.getConnection();
		boolean isUpdateSuccess = false;
		int updateCount = reviewDAO.updateArticle(conn, item);
		
		if(updateCount > 0) {
			JDBCUtil.commit(conn);
			isUpdateSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isUpdateSuccess;	
	}
}