package com.lec.ex01_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectManyMain {
	
	final static String DRV = "oracle.jdbc.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String USR = "scott";
	final static String PWD = "tiger";

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;	
	
	// 실습. select * from emp where empno >= ?;
	// 1) 사원정보출력 : 사원번호 사원명 직급 매니저 입사일 급여 커미션 부서번호
	// 2) while(rs.next())
	public static void main(String[] args) {
		try {
			Class.forName(DRV);
			conn = DriverManager.getConnection(URL, USR, PWD);
			String sql = "select * from emp where empno >= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			rs = pstmt.executeQuery();

			System.out.println("=".repeat(80));
			System.out.println("번호\t이름\t직급\t매니저\t입사일\t급여\t커미션\t부서번호");
			System.out.println("=".repeat(80));
			while(rs.next()) {
				int empno = rs.getInt(1);        
				String ename = rs.getString(2);     
				String job = rs.getString("job"); 
				int mgr = rs.getInt("MGR"); 
				String hiredate = rs.getString("hiredate");  
				int sal = rs.getInt("sal");  
				int comm = rs.getInt("comm");  
				int deptno = rs.getInt("deptno");	
				System.out.println(String.format("%d\t%s\t%s\t%d\t%s\t%d\t%d\t%d", 
						empno, ename, job, mgr, hiredate, sal, comm, deptno));
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
