package com.b_project.model.funding.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.b_project.model.funding.dao.FundingDAO;
import com.b_project.model.funding.model.FundingBean;
import com.b_project.util.JDBCUtil;

public class FundingListService {
	
	private FundingDAO fundingDAO = FundingDAO.getInstance();
	
	public int getListCount(String id) {
		
		Connection conn = JDBCUtil.getConnection();
		int listCount = fundingDAO.selectListCount(conn, id);
		JDBCUtil.close(conn);
		return listCount;
	}
	
	public ArrayList<FundingBean> getFundingList(int page, int limit, String id) {
		
		Connection conn = JDBCUtil.getConnection();
		ArrayList<FundingBean> fundingList = fundingDAO.selectFundingList(conn, page, limit, id);
		JDBCUtil.close(conn);
		return fundingList;
	}
}
