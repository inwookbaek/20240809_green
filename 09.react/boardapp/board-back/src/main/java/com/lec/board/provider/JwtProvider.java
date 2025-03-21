package com.lec.board.provider;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

/*
  Spring Security와 JWT 토큰을 사용하여 인증과 권한 부여를 처리하는 클래스이다.
  이 클래스에서 JWT 토큰의 생성, 복호화, 검증 기능을 구현하였다.
*/

@Log4j2
// JwtProvider는 강의원본로직, 이 로직을 JwtService로 대체해서 Bean등록(@Component)을 하지 않음
// @Component
public class JwtProvider {

	@Value("${jwt.secret}")
    private String secret; 
	
	@Value("${jwt.expiration}") 
    private long expiration;


    public String create(String email) {

    	String token = Jwts.builder()
                .subject(email) // setSubject -> subject
                .issuedAt(new Date()) // setIssuedAt -> issuedAt
                .expiration(new Date(System.currentTimeMillis() + expiration)) // setExpiration -> expiration
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // signWith에 SecretKey 사용
                .compact();
    	
        log.info("token ====> " + token);

        return token;
    }
    
    public String validate(String token) {
    	Claims claims = null;
    	
    	try {
    		claims = Jwts.claims().build();
    		
    		log.info("claims.getSubject() ===> " + claims.getSubject());
    		
    		return claims.getSubject();
    	} catch (Exception exception) {
    		exception.printStackTrace();
    		return null;
    	}
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes())) // setSigningKey -> verifyWith
                    .build()
                    .parseSignedClaims(token); 
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
    
}