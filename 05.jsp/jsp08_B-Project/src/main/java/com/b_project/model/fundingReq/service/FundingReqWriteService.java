package com.b_project.model.fundingReq.service;

import java.sql.Connection;

import com.b_project.model.fundingReq.dao.FundingReqDAO;
import com.b_project.model.fundingReq.model.FundingReq;
import com.b_project.util.JDBCUtil;

public class FundingReqWriteService {

	private FundingReqDAO fundingReqDAO = FundingReqDAO.getInstance();
	
	public boolean registerProject(FundingReq item) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isWriteSuccess = false;
		int insertCount = fundingReqDAO.insertProject(conn, item);
		
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
