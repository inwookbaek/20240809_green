package com.b_project.model.funding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.b_project.model.funding.model.FundingBean;
import com.b_project.model.project.dao.ProjectDAO;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.util.JDBCUtil;

public class FundingDAO {

	public static FundingDAO fundingDAO;
	private FundingDAO() {}
	public static FundingDAO getInstance() {
		if(fundingDAO == null) fundingDAO = new FundingDAO();
		return fundingDAO;
	}
	
	
	public int selectListCount(Connection conn, String id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		String sql = "select count(*) from funding where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<FundingBean> selectFundingList(Connection conn, int page, int limit, String id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FundingBean> fundingList = new ArrayList<FundingBean>();
		FundingBean bean = null;
		int startRow = (page-1) * limit;
		String sql = "select * from funding where id = ? order by fund_no desc limit ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new FundingBean();
				fundingBeanSetter(bean, rs);
				fundingList.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}	
		return fundingList;
	}
	
	public FundingBean selectFundingBean(Connection conn, int fund_no) {
		
		FundingBean bean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from funding where fund_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fund_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean = new FundingBean();
				fundingBeanSetter(bean, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return bean;
	}
	
	public int insertFunding(Connection conn, FundingBean funding) {
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		int project_no = funding.getProjectNo();
		
		String sql = "insert into funding(id, fund_date, fund, project_no) values(?, now(), ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, funding.getId());
			pstmt.setInt(2, funding.getMoney());
			pstmt.setInt(3, funding.getProjectNo());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		sql = "update funding_project set now_fund = now_fund + ?, supporter = supporter + 1 where project_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, funding.getMoney());
			pstmt.setInt(2, project_no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return insertCount;
	}
	
	void fundingBeanSetter(FundingBean bean, ResultSet rs) throws SQLException {
		
		bean.setFundNo(rs.getInt("fund_no"));
		bean.setId(rs.getString("id"));
		bean.setFundDate(rs.getTimestamp("fund_date"));
		bean.setMoney(rs.getInt("fund"));
		bean.setProjectNo(rs.getInt("project_no"));
	}
}
