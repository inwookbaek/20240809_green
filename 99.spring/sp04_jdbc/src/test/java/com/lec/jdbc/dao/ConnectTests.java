package com.lec.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectTests {

	@Test
	public void test1() {
		int v1 = 10;
		int v2 = 10;
		
		Assertions.assertEquals(v1, v2);
	}
	
	@Test
	public void testConnection() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/todo",
				"root",
				"12345");
		
		Assertions.assertNotNull(conn);
		conn.close();
	}
	
	@Test
	public void testHikariCP() throws Exception {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost:3306/todo");
		config.setUsername("root");
		config.setPassword("12345");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection conn = ds.getConnection();

        System.out.println(conn);

        conn.close();
	}
}
