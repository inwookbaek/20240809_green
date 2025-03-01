package com.lec.boot;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class DataSourceTests {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnection() throws Exception {
		@Cleanup Connection conn = dataSource.getConnection();
		log.info(conn);
		Assertions.assertNotNull(conn);
	}
}
