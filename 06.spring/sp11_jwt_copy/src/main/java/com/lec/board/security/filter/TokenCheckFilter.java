package com.lec.board.security.filter;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.filter.OncePerRequestFilter;

import com.lec.board.security.exception.AccessTokenException;
import com.lec.board.security.exception.AccessTokenException.TOKEN_ERROR;
import com.lec.board.util.JWTUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {
	private final JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String path = request.getRequestURI();
		
		if (!path.startsWith("/api/")) {
			filterChain.doFilter(request, response);		
			return;
		}
		
		log.info("--------------- Token Check Filter ---------------");
		log.info("JWTUtil ====> " + jwtUtil);

		try {
			validateAccessToken(request);
			
			filterChain.doFilter(request, response);
		} catch (AccessTokenException accessTokenException) {
			accessTokenException.sendResponseError(response);
		}
	}
	
	// 토큰을 검증하는 메서드
	private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {
		String headerStr = request.getHeader("Authorization");
		
		if (headerStr == null || headerStr.length() < 8) {
			throw new AccessTokenException(TOKEN_ERROR.UNACCEPT);
		}
		
		// Bearer는 생략
		String tokenType = headerStr.substring(0, 6);
		String tokenStr = headerStr.substring(7);
		
		if (tokenType.equalsIgnoreCase("Bearer") == false) {
			throw new AccessTokenException(TOKEN_ERROR.BADTYPE);
		}
		
		try {
			Map<String, Object> values = jwtUtil.validateToken(tokenStr);
			
			return values;
		} catch (MalformedJwtException e) {
			log.info("--------------- MalformedJwtException ---------------");
			throw new AccessTokenException(TOKEN_ERROR.MALFORM);
		} catch (SignatureException e) {
			log.info("--------------- SignatureException ---------------");
			throw new AccessTokenException(TOKEN_ERROR.BADSIGN);
		} catch (ExpiredJwtException e) {
			log.info("--------------- ExpiredJwtException ---------------");
			throw new AccessTokenException(TOKEN_ERROR.EXPIRED);
		}
	}
}