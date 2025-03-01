package com.lec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lec.jdbc.JDBCUtility;
import com.lec.model.MessageDTO;

public class MessageDAO {
	
	// Singleton
	private static MessageDAO messageDAO = new MessageDAO();
	private MessageDAO() {}
	
	public static MessageDAO getInstance() {
		return messageDAO;
	}
	
	public int insert(Connection conn, MessageDTO message) {
		PreparedStatement pstmt = null;
		String sql = "insert into message (guest_name, password, message) " 
				   + " values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getGuest_name());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}
	
	public List<MessageDTO> selectList(Connection conn, int firstRow, int endRow) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from message order by id desc limit ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow - firstRow);	
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<MessageDTO> listMessage = new ArrayList<>();
				do {
					listMessage.add(makeMessage(rs));
				} while(rs.next());
				return listMessage;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
	
	}
	
	public MessageDTO makeMessage(ResultSet rs) throws SQLException {
		MessageDTO message = new MessageDTO();
		message.setId(rs.getInt("id"));
		message.setGuest_name(rs.getString("guest_name"));
		message.setPassword(rs.getString("password"));
		message.setMessage(rs.getString("message"));
		return message;
	}

	public MessageDTO select(Connection conn, int id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from message where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) return makeMessage(rs);
			else return null;
 		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}		
	}	
	
	public int delete(Connection conn, int id) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from message where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public int update(Connection conn, int id, String msg) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update message set message=? where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, msg);
			pstmt.setInt(2, id);
			return pstmt.executeUpdate();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from message";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		} finally {
			JDBCUtility.close(null, stmt, rs);
		}
	}
}


















