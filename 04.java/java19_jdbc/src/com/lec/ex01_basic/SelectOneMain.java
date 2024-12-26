package com.lec.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectOneMain {
	
	final static String DRV = "oracle.jdbc.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String USR = "scott";
	final static String PWD = "tiger";
	
	
	public static void main(String[] args) {

		// PreparedStatement
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	

		try {
			Class.forName(DRV);
			conn = DriverManager.getConnection(URL, USR, PWD);
			String sql = "select * from emp where empno = ? and job = ?";
			
			// emp테이블에서 1건 읽기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 7369);
			pstmt.setString(2, "CLERK");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int empno = rs.getInt(1);        
				String ename = rs.getString(2);     
				String job = rs.getString("job"); 
				int mgr = rs.getInt("MGR"); 
				String hiredate = rs.getString("hiredate");  
				int sal = rs.getInt("sal");  
				int comm = rs.getInt("comm");  
				int deptno = rs.getInt("deptno");		
				System.out.print("[7369사원의 정보] ");
				System.out.println(empno + ", " 
						 + ", " + ename
						 + ", " + job
						 + ", " + mgr
						 + ", " + hiredate
						 + ", " + sal
						 + ", " + comm
						 + ", " + deptno);				
			} else {
				System.out.println("사원정보를 찾지 못했습니다!!!");
			}

		} catch (Exception e) {
			System.out.println("DB연결실패!!!");
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				// dummy
			}			
		}
	}
}
