package com.lec.board.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class JwtService {

    private final SecretKey secretKey; // SecretKey로 변경
    private final long expiration;

    public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
    	
    	log.info("[JwtService] ====> " + secret);
    	
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes()); // SecretKey 생성
        this.expiration = expiration;
    }

    // JWT 토큰 생성
    public String generateToken(String username) {
    	
    	
    	String token = Jwts.builder()
                .subject(username) // setSubject -> subject
                .issuedAt(new Date()) // setIssuedAt -> issuedAt
                .expiration(new Date(System.currentTimeMillis() + expiration)) // setExpiration -> expiration
                .signWith(secretKey) // signWith에 SecretKey 사용
                .compact();
    	
    	log.info("[generateToken] ====> " + username + "\n" + token);
    	
        return token;
    }

    // JWT 토큰에서 사용자 아이디 추출
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey) // setSigningKey -> verifyWith
                .build()
                .parseSignedClaims(token) // parseClaimsJws -> parseSignedClaims
                .getPayload(); // getBody -> getPayload
        return claims.getSubject();
    }

    // JWT 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey) // setSigningKey -> verifyWith
                .build()
                .parseSignedClaims(token); // parseClaimsJws -> parseSignedClaims
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}