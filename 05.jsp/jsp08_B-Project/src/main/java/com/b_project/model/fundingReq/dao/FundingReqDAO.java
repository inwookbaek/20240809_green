package com.b_project.model.fundingReq.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.b_project.model.fundingReq.model.FundingReq;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.util.JDBCUtil;

public class FundingReqDAO {

	public static FundingReqDAO fundingReqDAO;
	private FundingReqDAO() {}
	public static FundingReqDAO getInstance() {
		if(fundingReqDAO == null) fundingReqDAO = new FundingReqDAO();
		return fundingReqDAO;
	}

	// 글 개수
	public int selectListCount(Connection conn) {
	
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from funding_req";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시글 개수 가져오기 실패" + e);	
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return listCount;
	
	}
	
	// 글 목록 조회
	public ArrayList<FundingReq> selectArticleList(Connection conn, int page) {
		
		ArrayList<FundingReq> articleList = new ArrayList<FundingReq>();
		FundingReq board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from funding_req order by req_no desc " +
					" limit ?, 10";
		int startRow = (page-1) * 10;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new FundingReq();
				board.setReqNo(rs.getInt("req_no"));
				board.setSubject(rs.getString("subject"));
				board.setId(rs.getString("id"));
				board.setWrtDate(rs.getDate("wrt_date"));
				board.setMod_date(rs.getDate("mod_date"));
				board.setContent(rs.getString("content"));
				board.setFile(rs.getString("file"));
				
				articleList.add(board);
			}
		} catch(Exception e) {
			System.out.println("게시글 목록조회 실패 " + e);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return articleList;	
	}
	
	

	// 글 내용보기
	public FundingReq selectArticle(Connection conn, int req_no) {
		
		FundingReq board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from funding_req where req_no = " + req_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new FundingReq();
				board.setReqNo(rs.getInt("req_no"));
				board.setSubject(rs.getString("subject"));
				board.setId(rs.getString("id"));
				board.setWrtDate(rs.getDate("wrt_date"));
				board.setMod_date(rs.getDate("mod_date"));
				board.setContent(rs.getString("content"));
				board.setFile(rs.getString("file"));
			}	
		} catch (Exception e) {
			System.out.println("게시글 조회 실패 " + e);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return board;	
	}
	
	// 글등록
	public int insertProject(Connection conn, FundingReq item) {
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		String sql = "insert into funding_req(subject, id, wrt_date, mod_date, content, file) "
				   + " values(?,?,now(),now(),?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item.getSubject());
			pstmt.setString(2, item.getId());
			pstmt.setString(3, item.getContent());
			pstmt.setString(4, item.getFile());
			
			insertCount = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return insertCount;
	}
	
	// 글 수정
	public int updateArticle(Connection conn, FundingReq item) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = null;
		
		try {
			
			if(item.getFile() != null) {
				sql = "update funding_req set subject = ?, "
						+ "mod_date = now(), "
						+ "content = ?, "
						+ "file = ? "
						+ "where req_no = " + item.getReqNo();
			} else { 
				sql = "update funding_req set subject = ?, "
					+ "mod_date = now(), "
					+ "content = ? "
					+ "where req_no = " + item.getReqNo();	
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item.getSubject());
			pstmt.setString(2, item.getContent());

			if(item.getFile() != null) {
				pstmt.setString(3, item.getFile());
			}
	
			updateCount = pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("게시글 수정 실패 " + e);
		} finally {
			JDBCUtil.close(pstmt);
		}
		return updateCount;
	}	
		
		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, item.getSubject());
//			pstmt.setString(2, item.getContent());
//			pstmt.setInt(3, item.getReqNo());
//			updateCount = pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("게시글 수정 실패 " + e);
//		} finally {
//			JDBCUtil.close(pstmt);
//		}
//		return updateCount;
//	}
	
	// 글 삭제
	public int deleteArticle(Connection conn, int req_no) {
		
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "delete from funding_req where req_no = " + req_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글 삭제 실패 " + e);
		} finally {
			JDBCUtil.close(pstmt);
		}
		return deleteCount;
	}
	

	private void fundingSetter(FundingReq fundingReq, ResultSet rs) throws SQLException {
	
		fundingReq.setReqNo(rs.getInt("req_no"));
		fundingReq.setSubject(rs.getString("subject"));
		fundingReq.setId(rs.getString("id"));
		fundingReq.setWrtDate(rs.getTimestamp("wrt_date"));
		fundingReq.setMod_date(rs.getTimestamp("mod_date"));
		fundingReq.setContent(rs.getString("content"));
		fundingReq.setFile(rs.getString("file"));
		
	}
		
}
		
