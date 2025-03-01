package com.lec.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteMain {
	
	final static String DRV = "oracle.jdbc.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String USR = "scott";
	final static String PWD = "tiger";
	
	public static void main(String[] args) {
		// 실습. 9999사원 삭제하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int row = 0;
		try {
			Class.forName(DRV);
			conn = DriverManager.getConnection(URL, USR, PWD);
			sql = "delete from emp where empno = ?";	
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 9999);
			
			row = pstmt.executeUpdate();
			System.out.println("emp테이블에서 " + row + "건이 성공적으로 삭제 되었습니다!");			
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
