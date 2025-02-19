package com.b_project.model.fundingReq.service;

import java.sql.Connection;

import com.b_project.model.fundingReq.dao.FundingReqDAO;
import com.b_project.util.JDBCUtil;

public class FundingReqDeleteService {
	
	private FundingReqDAO fundingReqDAO = FundingReqDAO.getInstance();
	
	public boolean removearticle(int req_no) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isRemoveSuccess = false;
		int deleteCount = fundingReqDAO.deleteArticle(conn, req_no);
		
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
