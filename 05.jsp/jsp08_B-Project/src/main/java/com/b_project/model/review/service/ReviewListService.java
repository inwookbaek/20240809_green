package com.b_project.model.review.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.b_project.model.review.dao.ReviewDAO;
import com.b_project.model.review.model.ReviewBoardBean;
import com.b_project.util.JDBCUtil;

public class ReviewListService {

	private ReviewDAO reviewDAO = ReviewDAO.getInstance();
	
	public int getListCount(String searchOption, String searchWord) {
		
		Connection conn = JDBCUtil.getConnection();
		int listCount = reviewDAO.selectlistCount(conn, searchOption, searchWord);
		JDBCUtil.close(conn);
		return listCount;
	}

	public ArrayList<ReviewBoardBean> getArticleList(int page, int limit) {
		
		Connection conn = JDBCUtil.getConnection();
		ArrayList<ReviewBoardBean> articleList = null;
		articleList = reviewDAO.selectArticleList(conn, page, limit, "review_no"
				, false);
		JDBCUtil.close(conn);		
		return articleList;
	}
	
	public ArrayList<ReviewBoardBean> getArticleList(int page, int limit, String orderBy, boolean asc, String searchOption, String searchWord) {
	
		Connection conn = JDBCUtil.getConnection();
		ArrayList<ReviewBoardBean> articelList = null;
		articelList = reviewDAO.selectArticleList(conn, page, limit, orderBy, asc, searchOption, searchWord);
		JDBCUtil.close(conn);
		return articelList;
	}

}
