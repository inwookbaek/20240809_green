package com.b_project.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCUtil {

	public static Connection getConnection() {	
	
		Connection conn = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/B_project");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
		
	public static void close(ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) { 
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt != null) stmt.close();
		} catch (SQLException e) { 
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void close(Connection conn) {
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn != null) conn.rollback();
		} catch (SQLException e) { 
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void commit(Connection conn) {
		try {
			if(conn != null) conn.commit();
		} catch (SQLException e) { 
			rollback(conn);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
