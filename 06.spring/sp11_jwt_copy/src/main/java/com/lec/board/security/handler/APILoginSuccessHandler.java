package com.lec.board.security.handler;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;
import com.lec.board.util.JWTUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private final JWTUtil jwtUtil;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.info("Login Success Handler ..................");
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		log.info("username =====>", authentication + ", " + authentication.getName());
		
		Map<String, Object> claim = Map.of("mid", authentication.getName());
		
		// Access Token 유효기간을 1일
		String accessToken = jwtUtil.generateToken(claim, 1);
		
		// Refresh Token 유효기간을 30일
		String refreshToken = jwtUtil.generateToken(claim, 30);
		
		Gson gson = new Gson();
		
		Map<String, Object> keyMap = Map.of("accessToken", accessToken, 
											"refreshToken", refreshToken);
		
		String jsonStr = gson.toJson(keyMap);
		
		log.info("jsonStr ===> " + jsonStr);
		
		response.getWriter().println(jsonStr);
	}

}
