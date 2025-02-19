package com.b_project.model.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.util.JDBCUtil;

public class ProjectDAO {

//	싱글톤
	public static ProjectDAO projectDAO;
	private ProjectDAO() {}
	public static ProjectDAO getInstance() {
		if(projectDAO == null) projectDAO = new ProjectDAO();
		return projectDAO;
	}


	
	public int selectListCount(Connection conn, String searchOption, String searchWord) {
		return selectListCount(conn, searchOption, searchWord, false);
	}

	public int selectListCount(Connection conn, String searchOption, String searchWord, boolean finishedOrNot) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		String sql = null;
		
		if(finishedOrNot) {
//			제목으로 검색한 글의 수
			if(searchOption.equals("제목")) {
				sql = "select count(*) from funding_project where subject like \'%" + searchWord + "%\'"
					+ " and DATEDIFF(END_DATE, NOW()) < 0";
//			제목과 내용으로 검색한 글의 수
			} else if(searchOption.equals("제목과내용")) {
				sql = "select count(*) from funding_project where (subject like \'%" + searchWord 
					+ "%\' or content like \'%" + searchWord + "%\')"
					+ " and DATEDIFF(END_DATE, NOW()) < 0";
//			모든 글의 수.
			} else {
				sql = "select count(*) from funding_project"
					+ " where DATEDIFF(END_DATE, NOW()) < 0";
			}
		} else {
//			제목으로 검색한 글의 수
			if(searchOption.equals("제목")) {
				sql = "select count(*) from funding_project where subject like \'%" + searchWord + "%\'"
					+ " and DATEDIFF(END_DATE, NOW()) >=0";
//			제목과 내용으로 검색한 글의 수
			} else if(searchOption.equals("제목과내용")) {
				sql = "select count(*) from funding_project where (subject like \'%" + searchWord 
					+ "%\' or content like \'%" + searchWord + "%\')"
					+ " and DATEDIFF(END_DATE, NOW()) >=0";
//			모든 글의 수.
			} else {
				sql = "select count(*) from funding_project"
					+ " where DATEDIFF(END_DATE, NOW()) >=0";
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
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
	
	
	public ArrayList<ProjectBoardBean> selectArticleList(Connection conn, int page, int limit, String orderBy, boolean asc) {
		
		return selectArticleList(conn, page, limit, orderBy, asc, "", "");
	}
	
	public ArrayList<ProjectBoardBean> selectArticleList(Connection conn, int page, int limit, String orderBy, boolean asc, String searchOption, String searchWord) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProjectBoardBean> articleList = new ArrayList<ProjectBoardBean>();
		ProjectBoardBean boardBean = null;
		int startRow = (page-1) * limit;
		String sort = null;
		String sql = null;
		
		if(asc) sort = "asc";
		else sort="desc";
		
//		제목으로 검색한 글 목록
		if(searchOption.equals("제목")) {
			sql = "select * from funding_project where subject like \'%" + searchWord + "%\'"
				+ " and DATEDIFF(END_DATE, NOW()) >=0"
				+ " order by " + orderBy + " " + sort + " limit ?, ?";
				
//		제목과 내용으로 검색한 글 목록
		} else if(searchOption.equals("제목과내용")) {
			sql = "select * from funding_project where subject like \'%" + searchWord + "%\' or content like \'%" + searchWord + "%\'"
				+ " and DATEDIFF(END_DATE, NOW()) >=0"
				+ " order by " + orderBy + " " + sort + " limit ?, ?";
//		모든 글 목록
		} else {
			sql = "select * from funding_project" 
				+ " where DATEDIFF(END_DATE, NOW()) >=0"
				+ " order by " + orderBy + " " + sort + " limit ?, ?";
				
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardBean = new ProjectBoardBean();
				boardBeanSetter(boardBean, rs);
				articleList.add(boardBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}	
		return articleList;
	}
	
	public ArrayList<ProjectBoardBean> selectArticleList(Connection conn, int page, int limit, String orderBy, boolean asc, String searchOption, String searchWord, boolean finishedOrNot) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProjectBoardBean> articleList = new ArrayList<ProjectBoardBean>();
		ProjectBoardBean boardBean = null;
		int startRow = (page-1) * limit;
		String sort = null;
		String sql = null;
		
		if(asc) sort = "asc";
		else sort="desc";
		
		if(finishedOrNot) {
//			제목으로 검색한 글 목록
			if(searchOption.equals("제목")) {
				sql = "select * from funding_project where subject like \'%" + searchWord + "%\'"
					+ " and DATEDIFF(END_DATE, NOW()) < 0"
					+ " order by " + orderBy + " " + sort + " limit ?, ?";
					
//			제목과 내용으로 검색한 글 목록
			} else if(searchOption.equals("제목과내용")) {
				sql = "select * from funding_project where subject like \'%" + searchWord + "%\' or content like \'%" + searchWord + "%\'"
					+ " and DATEDIFF(END_DATE, NOW()) < 0"
					+ " order by " + orderBy + " " + sort + " limit ?, ?";
//			모든 글 목록
			} else {
				sql = "select * from funding_project" 
					+ " where DATEDIFF(END_DATE, NOW()) < 0"
					+ " order by " + orderBy + " " + sort + " limit ?, ?";
					
			}
		} else {
//			제목으로 검색한 글 목록
			if(searchOption.equals("제목")) {
				sql = "select * from funding_project where subject like \'%" + searchWord + "%\'"
					+ " and DATEDIFF(END_DATE, NOW()) >=0"
					+ " order by " + orderBy + " " + sort + " limit ?, ?";
					
//			제목과 내용으로 검색한 글 목록
			} else if(searchOption.equals("제목과내용")) {
				sql = "select * from funding_project where subject like \'%" + searchWord + "%\' or content like \'%" + searchWord + "%\'"
					+ " and DATEDIFF(END_DATE, NOW()) >=0"
					+ " order by " + orderBy + " " + sort + " limit ?, ?";
//			모든 글 목록
			} else {
				sql = "select * from funding_project" 
					+ " where DATEDIFF(END_DATE, NOW()) >=0"
					+ " order by " + orderBy + " " + sort + " limit ?, ?";
					
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardBean = new ProjectBoardBean();
				boardBeanSetter(boardBean, rs);
				articleList.add(boardBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}	
		return articleList;
	}
	
	public ProjectBoardBean selectArticle(Connection conn, int project_no) {
		
		ProjectBoardBean bean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from funding_project where project_no = " + project_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean = new ProjectBoardBean();
				boardBeanSetter(bean, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return bean;
	}
	
	public int insertArticle(Connection conn, ProjectBoardBean item) {
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		String sql = "insert into funding_project(subject, wrt_date, mod_date, end_date, content, obj_fund, now_fund, supporter, category, read_cnt, file, creator) "
				   + "values(?,now(),now(),?,?,?,0,0,?,0,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item.getSubject());
			pstmt.setDate(2, new Date(item.getEndDate().getTime()));
			pstmt.setString(3, item.getContent());
			pstmt.setInt(4, item.getObjFund());
			
			if(item.getCategory()==null || item.getCategory().isEmpty()) {
				pstmt.setString(5, "기타");
			} else {
				pstmt.setString(5, item.getCategory());
			}
			pstmt.setString(6, item.getAttachedFile());
			pstmt.setString(7, item.getCreator());
			
			insertCount = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return insertCount;
	}
	
	public int deleteArticle(Connection conn, int project_no) {
		
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		
		String sql = "delete from funding_project where project_no = " + project_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return deleteCount;
	}
	
	public int updateArticle(Connection conn, ProjectBoardBean item) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = null;
		
		try {
			
			if(item.getAttachedFile() != null) {
				sql = "update funding_project set subject = ?, "
						+ "mod_date = now(), "
						+ "end_date = ?, "
						+ "content = ?, "
						+ "obj_fund = ?, "
						+ "category = ?, "
						+ "creator = ?, "
						+ "file = ? "
						+ "where project_no = " + item.getProjectNo();	
			} else {
				sql = "update funding_project set subject = ?, "
						+ "mod_date = now(), "
						+ "end_date = ?, "
						+ "content = ?, "
						+ "obj_fund = ?, "
						+ "category = ?, "
						+ "creator = ? "
						+ "where project_no = " + item.getProjectNo();	
			}
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item.getSubject());
			pstmt.setDate(2, new Date(item.getEndDate().getTime()));
			pstmt.setString(3, item.getContent());
			pstmt.setInt(4, item.getObjFund());
				
			if(item.getCategory()==null || item.getCategory().isEmpty()) {
				pstmt.setString(5, "기타");
			} else {
				pstmt.setString(5, item.getCategory());
			}
			
			pstmt.setString(6, item.getCreator());
			
			if(item.getAttachedFile() != null) {
				pstmt.setString(7, item.getAttachedFile());
			}
						
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	
	public int updateReadCount(Connection conn, int project_no) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		String sql = "update funding_project set read_cnt = read_cnt + 1 where project_no = " + project_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return updateCount;
	}
	
	public int getMaxProjectNo(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxNo = 0;
		
		String sql = "select max(project_no) from funding_project";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) maxNo = rs.getInt(1);		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return maxNo;
	}
	
	void boardBeanSetter(ProjectBoardBean boardBean, ResultSet rs) throws SQLException {
		
		boardBean.setProjectNo(rs.getInt("project_no"));
		boardBean.setSubject(rs.getString("subject"));
		boardBean.setWrtDate(rs.getTimestamp("wrt_date"));
		boardBean.setModDate(rs.getTimestamp("mod_date"));
		boardBean.setEndDate(rs.getDate("end_date"));
		boardBean.setContent(rs.getString("content"));
		boardBean.setObjFund(rs.getInt("obj_fund"));
		boardBean.setNowFund(rs.getInt("now_fund"));
		boardBean.setSupporterNo(rs.getInt("supporter"));
		boardBean.setCategory(rs.getString("category"));
		boardBean.setReadCnt(rs.getInt("read_cnt"));
		boardBean.setAttachedFile(rs.getString("file"));
		boardBean.setCreator(rs.getString("creator"));
	}
}
