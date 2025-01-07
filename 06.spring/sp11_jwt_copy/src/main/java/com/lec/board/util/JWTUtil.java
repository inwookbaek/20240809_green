package com.lec.board.util;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class JWTUtil {

	@Value("${com.lec.jwt.secret}")
	private String username;
	
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long expiration = 1000;  // token 유효기간 : 1시간 = 3,600,000 sec
	
	public String generateToken(String username) {
		
		log.info("gernerateKey(username) ===> " + username);
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() * expiration))
				.signWith(key)
				.compact();
	}
	
	// token에서 username 추출
	public String getUsernameFromToken(String token) {
		return getClaimsFromToken(token).getSubject();
	}
	
	public Claims getClaimsFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	// token검사
	public boolean isTokenValid(String token) {
		return !isTokenExpired(token);
	}
	
	public boolean isTokenExpired(String token) {
		return getClaimsFromToken(token).getExpiration().before(new Date());
	}

	// Token생성
	public String generateToken(Map<String, Object> valueMap, int days) {
		
		log.info("gernerateKey(username) ===> " + username);
		
		// 헤더부분
		Map<String, Object> headers = new HashMap<>();
		headers.put("typ", "JWT");
		headers.put("alg", "HS256");
		
		// 페이로드부분
		Map<String, Object> payloads = new HashMap<>();
		payloads.putAll(valueMap);
		
		// 유효시간 : 테스트시에는 짧게 정의
		int time = (1) * days;   // 테스트단위는 분단위
		// int time = (60 * 24) * days;   // 테스트단위는 일(60*24)단위
		
		return Jwts.builder()
				.setSubject(username)
				.setHeader(headers)
				.setClaims(payloads)
				.setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
				.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()))
				.signWith(key)
				.compact();
	}
	
	// Token검증
	public Map<String, Object> validateToken(String token) throws JwtException {
		
		Map<String, Object> claim = null;
		
		claim = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token) // 파싱 및 검증, 실패시 에러
					.getBody();
		
		return claim;
	}
}
