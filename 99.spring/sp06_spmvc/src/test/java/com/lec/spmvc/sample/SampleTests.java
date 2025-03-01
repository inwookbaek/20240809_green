package com.lec.spmvc.sample;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
@SpringBootTest
public class SampleTests {

	@Autowired
	private SampleService sampleservice;
	
	@Autowired
	private DataSource dataSource;

	@Test
	public void testService() {
		log.info(sampleservice);
		Assertions.assertNotNull(sampleservice);
	}
	
	@Test
	public void testConnection() throws Exception {
		Connection conn = dataSource.getConnection();
		log.info(conn);
		Assertions.assertNotNull(dataSource);
		conn.close();
	}
}
