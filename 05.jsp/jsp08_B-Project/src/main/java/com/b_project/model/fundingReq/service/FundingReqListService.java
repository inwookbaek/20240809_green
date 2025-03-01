package com.b_project.model.fundingReq.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.b_project.model.fundingReq.dao.FundingReqDAO;
import com.b_project.model.fundingReq.model.FundingReq;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.util.JDBCUtil;

public class FundingReqListService {

	private FundingReqDAO fundingReqDAO = FundingReqDAO.getInstance();
	
	public int getListCount() {	
		
		Connection conn = JDBCUtil.getConnection();
		FundingReqDAO fundingReqDAO = FundingReqDAO.getInstance();
		int listCount = fundingReqDAO.selectListCount(conn);
		
		JDBCUtil.close(conn);
		return listCount;
	}
	
	public ArrayList<FundingReq> getArticleList(int page, int limit) throws Exception {
				
		Connection conn = JDBCUtil.getConnection();
		ArrayList<FundingReq> articleList = null;
		
		articleList = fundingReqDAO.selectArticleList(conn, page);
	
		JDBCUtil.close(conn);
		return articleList;
	}
}
