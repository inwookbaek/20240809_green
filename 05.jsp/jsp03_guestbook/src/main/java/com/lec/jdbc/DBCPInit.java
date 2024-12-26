package com.lec.jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

// Servlet <- GenericServlet <- HttpServlet
public class DBCPInit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		String drv = getInitParameter("drv");
		
		try {
			Class.forName(drv);			
		} catch (Exception e) {
			throw new RuntimeException("JDBC 드라이버로딩 실패!!!");
		}
		
		String url = getInitParameter("url");
		String usr = getInitParameter("usr");
		String pwd = getInitParameter("pwd");
				
		// 1. Connection 생성 및 유효성검증
		ConnectionFactory connFactory = new DriverManagerConnectionFactory(url, usr, pwd);
		PoolableConnectionFactory poolFactory = new PoolableConnectionFactory(connFactory, null);
		poolFactory.setValidationQuery("select 1"); // 커넥션유효성 검사 mysql
		// poolFactory.setValidationQuery("select * from dual"); oracle
		
		// 2. Connection Pool에대한 설정
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setTimeBetweenEvictionRunsMillis(100L * 60L * 5L); // 사용가능한 커넥션 검사주기
		poolConfig.setTestWhileIdle(true); // 커넥션풀에 보관중인 커넥션의 유효여부를 검사
		poolConfig.setMinIdle(5); // 커넥션최소갯수
		poolConfig.setMaxIdle(50); // 커넥션최대갯수
		
		// 3. 생성된 커넥션을 커넥션풀에 연결
		GenericObjectPool<PoolableConnection> connPool 
			= new GenericObjectPool<>(poolFactory, poolConfig);
		poolFactory.setPool(connPool);
		
		try {
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = getInitParameter("poolName");
			driver.registerPool(poolName, connPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
