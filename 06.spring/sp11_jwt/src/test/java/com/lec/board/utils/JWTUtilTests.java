package com.lec.board.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.board.util.JWTUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
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
	
	@Test
	public void testToken() {
		
		String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtaWQiOiJhcGl1c2VyMTAiLCJpYXQiOjE3MzM0NDY4MzEsImV4cCI6MTczMzUzMzIzMX0.ZhZCLJfIUQOYqvp-QLO_-fXVrPBiGzzHLQDA-zYdbpo";
		log.info(jwtStr);
		
		Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
		
		log.info("MID: " + claim.get("mid"));
		log.info("EMAIL: " + claim.get("email"));
		
	}	
}
