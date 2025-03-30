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

    // JWT 서명(Signature)에 사용할 SecretKey
    private final SecretKey secretKey;
    
    // JWT의 만료 시간 (밀리초 단위)
    private final long expiration;
    
    /**
     * JwtService 생성자
     * @param secret 환경 설정에서 주입된 JWT 서명 키
     * @param expiration 환경 설정에서 주입된 JWT 만료 시간
     */
    public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
        
        log.info("[JwtService] ====> JWT Secret Key Length: " + secret.length());
        
        // SecretKey 생성 (HMAC SHA 알고리즘을 사용하여 키를 생성)
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        
        // 만료 시간 설정
        this.expiration = expiration;
    }
    
    /**
     * JWT 토큰 생성
     * @param username 사용자 이름 (토큰의 subject로 저장)
     * @return 생성된 JWT 토큰 문자열
     */
    public String generateToken(String username) {

        // 현재 시간 기준으로 만료 시간 설정
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        
        // JWT 생성
        String token = Jwts.builder()
                .subject(username)  // subject 설정 (사용자 식별 정보)
                .issuedAt(now)  // 발행 시간 설정
                .expiration(expiryDate)  // 만료 시간 설정
                .signWith(secretKey)  // SecretKey를 사용한 서명(Signature) 추가
                .compact();  // 최종 JWT 문자열 생성
        
        // log.info("[generateToken] ====> 생성된 토큰: " + token);
        
        return token;
    }

    /**
     * JWT 토큰에서 사용자 이름 추출
     * @param token JWT 토큰 문자열
     * @return 추출된 사용자 이름 (subject)
     */
    public String getUsernameFromToken(String token) {
        
        // JWT 파싱하여 Claims(페이로드) 정보 가져오기
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)  // 서명 검증
                .build()
                .parseSignedClaims(token)  // 토큰 해석
                .getPayload();  // Claims(페이로드) 정보 가져오기
        
        return claims.getSubject();  // subject(사용자명) 반환
    }

    /**
     * JWT 토큰의 유효성 검증
     * @param token 검증할 JWT 토큰
     * @return 유효한 토큰이면 true, 그렇지 않으면 false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey)  // 서명 검증 수행
                .build()
                .parseSignedClaims(token);  // 토큰 해석 (유효성 검증)
            return true;
        } catch (Exception e) {
            log.warn("[validateToken] ====> 유효하지 않은 토큰: " + token);
            return false;
        }
    }
}
