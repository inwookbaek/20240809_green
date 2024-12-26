package com.b_project.model.funding.service;

import java.sql.Connection;

import com.b_project.model.funding.dao.FundingDAO;
import com.b_project.model.funding.model.FundingBean;
import com.b_project.util.JDBCUtil;

public class FundingSupportService {

	private FundingDAO fundingDAO = FundingDAO.getInstance();
	
	public boolean registerFunding(FundingBean funding) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isWriteSuccess = false;
		int insertCount = fundingDAO.insertFunding(conn, funding);
		
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
