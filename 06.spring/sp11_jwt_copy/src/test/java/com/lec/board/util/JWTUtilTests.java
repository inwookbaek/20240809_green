package com.lec.board.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.board.util.JWTUtil;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class JWTUtilTests {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Test
	public void testGenerate() {
		String jwtStr = jwtUtil.generateToken("gilbaek");
		log.info("1. generateToken " + jwtStr);
		log.info("2. getUsernameFromToken " + jwtUtil.getUsernameFromToken(jwtStr));
		log.info("3. isTokenValid " + jwtUtil.isTokenValid(jwtStr));

	}
	
	@Test
	public void testGenerate1() { 
		Map<String, Object> claimMap = Map.of("mid", "HELLO");		
		String jwtStr = jwtUtil.generateToken(claimMap, 1);	
		log.info(jwtStr);
	}
	
	@Test
	public void testAll() {

			String jwtStr = jwtUtil.generateToken(Map.of("mid","AAAA","email","aaaa@bbb.com"), 1);
			log.info(jwtStr);
			
			Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
			
			log.info("MID: " + claim.get("mid"));
			log.info("EMAIL: " + claim.get("email"));

	}	
}
