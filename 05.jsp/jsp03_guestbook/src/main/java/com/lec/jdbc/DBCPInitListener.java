package com.lec.jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String pool 
			= sce.getServletContext().getInitParameter("poolConfig");
		
		Properties prop = new Properties();
		
		try {
			prop.load(new StringReader(pool));
			Class.forName(prop.getProperty("drv"));			
		
			String url = prop.getProperty("url");
			String usr = prop.getProperty("usr");
			String pwd = prop.getProperty("pwd");
			
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(url, usr, pwd);
			PoolableConnectionFactory poolFactory = new PoolableConnectionFactory(connFactory, null);
			poolFactory.setValidationQuery("select 1"); // 커넥션유효성 검사 mysql
	
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(100L * 60L * 5L); 
			poolConfig.setTestWhileIdle(true); 
			poolConfig.setMinIdle(5); 
			poolConfig.setMaxIdle(50); 
			
			GenericObjectPool<PoolableConnection> connPool 
				= new GenericObjectPool<>(poolFactory, poolConfig);
			poolFactory.setPool(connPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("guestbook", connPool);		
		} catch (Exception e) {
			throw new RuntimeException("DB환경설정파일 로딩 실패!!");
		}		
	}
}
