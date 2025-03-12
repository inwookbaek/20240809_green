package com.lec.board.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

/*
  Spring Security와 JWT 토큰을 사용하여 인증과 권한 부여를 처리하는 클래스이다.
  이 클래스에서 JWT 토큰의 생성, 복호화, 검증 기능을 구현하였다.
*/

@Log4j2
@Component
public class JwtProvider {

	// @Value("${jwt.secret}")
	private String SECRET_KEY = "S3cr3tK3y";
	
	public String create(String email) {
		
		Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
		
		String jwt = Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(expiredDate)
				.compact();		
		
		log.info("SECRET_KEY ====> " + SECRET_KEY);
		log.info("jwt ====> " + jwt);
		
		return jwt;
	}
 
	public String validate(String jwt) {
		
		Claims claims = null;
		
		try {
			claims = Jwts.parser()
					.setSigningKey(SECRET_KEY)
					.parseClaimsJws(jwt)
					.getBody();
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
		
		return claims.getSubject();
	}
}
