package com.lec.jpa;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.jpa.repository.BoardRepository;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class DataSourceTests {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void testConnection() throws SQLException {
		
		@Cleanup Connection conn = dataSource.getConnection();
		log.info("........... " + conn);
		Assertions.assertNotNull(conn);
		conn.close();
	}

	@Test
	public void testGetTime() {
		String now = boardRepository.getTime();
		log.info("........... " + now);
		Assertions.assertNotNull(now);
	}
	
	@Test
	public void testBoardInsert() {
		
	}
	
}
