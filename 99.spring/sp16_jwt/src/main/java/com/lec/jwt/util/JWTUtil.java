package com.lec.jwt.util;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JWTUtil {

	@Value("${com.lec.jwt.secret}")
	private String username;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiration = 1000; // Token validity: 1 hour(3600000)
	
    public String generateToken(String username){

        log.info("generateKey===> " + username);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token) { 
        return getClaimsFromToken(token).getSubject();
    }
    
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }
    
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }    

    private boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }
 
//  freelec p799    
    public String generateToken(Map<String, Object> valueMap, int days){
    	
        log.info("generateKey.... " + username);
        
    	//헤더 부분
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.putAll(valueMap);
        
        //테스트 시에는 짧은 유효 기간
        //int time = (1) * days; //테스트는 분단위로 나중에 60*24 (일)단위변경

        //10분 단위로 조정
        int time = (60*24) * days; //테스트는 분단위로 나중에 60*24 (일)단위변경        
        
    	return Jwts.builder()
    			.setSubject(username)
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()))
    			.signWith(key)
    			.compact();
    	
    }    
    public Map<String, Object> validateToken(String token) {

        Map<String, Object> claim = null;

        claim = Jwts.parserBuilder()
	                .setSigningKey(key)    // Set Key
	                .build()
	                .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
	                .getBody();        
        
        return claim;
    } 
//  freelec p799       
}
