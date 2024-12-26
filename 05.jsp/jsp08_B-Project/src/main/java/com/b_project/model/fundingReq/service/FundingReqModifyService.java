package com.b_project.model.fundingReq.service;

import java.sql.Connection;

import com.b_project.model.fundingReq.dao.FundingReqDAO;
import com.b_project.model.fundingReq.model.FundingReq;
import com.b_project.util.JDBCUtil;

public class FundingReqModifyService {
	
		private FundingReqDAO fundingReqDAO = FundingReqDAO.getInstance();
		
		public boolean modifyFunding(FundingReq item) {
			
			boolean isUpdateSuccess = false;
			Connection conn = JDBCUtil.getConnection();
			int updateCount = fundingReqDAO.updateArticle(conn, item);
			
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
