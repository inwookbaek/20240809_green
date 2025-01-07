package com.b_project.model.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.b_project.model.question.model.Answer;
import com.b_project.util.JDBCUtil;

public class AnswerDAO {
	
	public static AnswerDAO answerDAO;
	private AnswerDAO() {}
	public static AnswerDAO getInstance() {
		if(answerDAO == null) answerDAO = new AnswerDAO();
		return answerDAO;
	}
	
	public Answer selectArticle(Connection conn, int q_no) {
		
		Answer answer = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from answer where q_no = " + q_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				answer = new Answer();
				answer.setqNo(rs.getInt("q_no"));
				answer.setWrtDate(rs.getTimestamp("wrt_date"));
				answer.setModDate(rs.getTimestamp("mod_date"));
				answer.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return answer;
	}
	
	
	public int insertArticle(Connection conn, Answer answer) {
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		String sql = "insert into answer values(?, now(), now(), ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, answer.getqNo());
			pstmt.setString(2, answer.getContent());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return insertCount;
	}
	
	public int deleteArticle(Connection conn, int q_no) {
		
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		
		String sql = "delete from answer where q_no = " + q_no;
		
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
	public int updateArticle(Connection conn, Answer answer) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update answer set content = ?, mod_date = now() where q_no = ?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, answer.getContent());
			pstmt.setInt(2, answer.getqNo());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return updateCount;
	} 
}
