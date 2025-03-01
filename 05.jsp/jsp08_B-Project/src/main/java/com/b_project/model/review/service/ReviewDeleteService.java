package com.b_project.model.review.service;

import java.sql.Connection;


import com.b_project.model.review.dao.ReviewDAO;
import com.b_project.util.JDBCUtil;

public class ReviewDeleteService {

	private ReviewDAO reviewDAO = ReviewDAO.getInstance();
	
	public boolean removeArticle(int review_no) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isRemoveSuccess = false;
		int deleteCount = reviewDAO.deleteArticle(conn, review_no);
		
		if(deleteCount > 0) {
			JDBCUtil.commit(conn);
			isRemoveSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isRemoveSuccess;
	}
}


