package com.lec.ex03_connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lec.ex02_board.BoardVO;

public class MyDBConnection {

	public static void main(String[] args) throws Exception {
		
		Connection conn = JDBCConnector.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from board");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBno(rs.getInt(1));
				board.setSubject(rs.getString(2));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setCrtdate(rs.getString("crtdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				System.out.println(board.toString());
			}
		} catch (Exception e) {
			System.out.println("DB접속실패!!!");
			e.printStackTrace();
		} finally {
			JDBCConnector.close(conn, pstmt, rs);
		}
	}
}
