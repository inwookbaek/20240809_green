package com.lec.board.security.filter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.lec.board.security.exception.RefreshTokenException;
import com.lec.board.util.JWTUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {
	
	private final String refreshPath;
	private final JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
				HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String path = request.getRequestURI();
		
		if(!path.equals(refreshPath)) {
			log.info("skip refresh token filter............");
			filterChain.doFilter(request, response);
			return;
		}
		
		log.info("Refresh Token Filter 실행.................");
		
		// 전송된 JSON에서 accessToken과 refreshToken을 획득
		Map<String, String> tokens = parseRequestJSON(request);
		
		String accessToken = tokens.get("accessToken");
		String refreshToken = tokens.get("refreshToken");
		
		log.info("accessToken ===> " + accessToken);
		log.info("refreshToken ==> " + refreshToken);
		
		try {
			checkAccessToken(accessToken);
		} catch (RefreshTokenException refreshTokenException) {
			refreshTokenException.sendResponseError(response);
		}
		
		Map<String, Object> refreshClaims = null;
		try {
			refreshClaims = checkRefreshToken(refreshToken);
			log.info(refreshClaims);
			
		} catch (RefreshTokenException refreshTokenException) {
			refreshTokenException.sendResponseError(response);
			return;
		}
		
		// RefrehToken이 얼마 남지 않았을 경우 
		Integer exp = (Integer) refreshClaims.get("exp");
		
		Date expTime = new Date(Instant.ofEpochMilli(exp).toEpochMilli() * 1000);
		Date current = new Date(System.currentTimeMillis());
		
		// 만료시간과 현재시간의 간격
		// 만일 3일미만일 경우에는 Refresh Token도 다시 생성
		long gapTime = (expTime.getTime() - current.getTime());
		
		log.info("----------------------------------------");
		log.info("current ===> " + current);
		log.info("expTime ===> " + expTime);
		log.info("gapTime ===> " + gapTime);

		String mid = (String) refreshClaims.get("mid");
		
		// 여기까지 실행되면 무조건 Access Token은 새로 생성된 상태
		String accessTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 1);
		String refreshTokenValue = tokens.get("refreshToken");
		
		// Refresh Token 3일도 안남았을 경우
		if(gapTime < (1000 * 60 * 3)) {
			log.info("새로운 Refresh Token 요청 ................");
			refreshTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 30);
		}
		log.info("Refresh Token 결과 ................");
		log.info("accessToken ===> " + accessToken);
		log.info("refreshToken ==> " + refreshToken);
		
		sendTokens(accessTokenValue, refreshTokenValue, response);
	}

	private void sendTokens(String accessTokenValue, String refreshTokenValue,
			HttpServletResponse response) {
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(Map.of("accessToken", accessTokenValue,
				"refreshToken", refreshTokenValue));
		
		try {
			response.getWriter().println(jsonStr);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}

	private Map<String, String> parseRequestJSON(HttpServletRequest request) {
		
		// JSON데이터를 분석해서 mid, mpw전달된 값을 Map으로 처리
		try(Reader reader = new InputStreamReader(request.getInputStream())) {
			Gson gson = new Gson();
			return gson.fromJson(reader, Map.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	private void checkAccessToken(String accessToken) throws RefreshTokenException {
		try {
			jwtUtil.validateToken(accessToken);			
		} catch (ExpiredJwtException e) {
			log.info("Accesstoken이 만료가 되었습니다. 재발급 or 발급 받으세요!");
		} catch (Exception e) {
			throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_ACCESS);
		}
	}
	
	private Map<String, Object> checkRefreshToken(String refreshToken) 
			throws RefreshTokenException {
		try {
			Map<String, Object> values = jwtUtil.validateToken(refreshToken);	
			return values;
		} catch (ExpiredJwtException expiredJwtException) {
			throw new RefreshTokenException(RefreshTokenException.ErrorCase.OLD_REFRESH);
		} catch (MalformedJwtException malformedJwtException) {
			log.info("MalformedJwtException ......................");
			new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
		} catch (Exception e) {
			e.printStackTrace();
			new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
		}
		return null;
	}
}
