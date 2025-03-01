package com.b_project.model.fundingReq.service;

import java.sql.Connection;

import com.b_project.model.fundingReq.dao.FundingReqDAO;
import com.b_project.model.fundingReq.model.FundingReq;
import com.b_project.util.JDBCUtil;

public class FundingReqDetailService {
	
	public FundingReq getArticle(int req_no) throws Exception {
		
	FundingReq item = null;
	Connection conn = JDBCUtil.getConnection();
	FundingReqDAO fundingReqDAO = FundingReqDAO.getInstance();
	
	item = fundingReqDAO.selectArticle(conn, req_no);
	JDBCUtil.close(conn);
	return item;

	}
}
