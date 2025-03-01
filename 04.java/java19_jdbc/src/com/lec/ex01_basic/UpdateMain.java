package com.lec.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateMain {
	
	final static String DRV = "oracle.jdbc.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String USR = "scott";
	final static String PWD = "tiger";
	
	public static void main(String[] args) {
		// 실습. 9999사원정보를 수정하기
		// 소향, 부장, 100, 9999
		// sql = "update emp set ename=?, job=? comm=? "
	    //     + " where empno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int row = 0;
		
		try {
			Class.forName(DRV);
			conn = DriverManager.getConnection(URL, USR, PWD);
			sql = "update emp set ename=?, job=?, comm=? where empno = ?";	
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "소향");
			pstmt.setString(2, "부장");
			pstmt.setInt(3, 100);
			pstmt.setInt(4, 9999);
			
			row = pstmt.executeUpdate();
			System.out.println("emp테이블에 " + row + "건이 성공적으로 수정 되었습니다!");			
		} catch (Exception e) {
			System.out.println("DB연결실패!!!");
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// dummy
			}			
		}			
	}

}
