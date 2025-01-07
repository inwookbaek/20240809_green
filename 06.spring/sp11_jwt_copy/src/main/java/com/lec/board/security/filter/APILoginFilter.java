package com.lec.board.security.filter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
/*
	AbstractAuthenticationProcessingFilter는 로그인처리를 담당하기 때문에 다른 필터들과
	달리 로그인을 처리하는 경로에 대한 설정과 실제 인증처리를 담당하는 AuthenticationManager
	객체의 설정을 반드시 필요하다. 이 설정은 CustomSecurityConfig를 이용해서 설정
*/
@Log4j2
public class APILoginFilter extends AbstractAuthenticationProcessingFilter {
	
	public APILoginFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		log.info("APILoginFilter .............................");
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			log.info("실습(APILogin)에서는 GET방식을 허용하지 않습니다!");
			return null;
		}
		
		Map<String, String> jsonData = parseRequestJSON(request);
		log.info("jsonData ===> " + jsonData);
		
		UsernamePasswordAuthenticationToken authenticationToken 
			= new UsernamePasswordAuthenticationToken(jsonData.get("mid"), jsonData.get("mpw"));		
		
		return getAuthenticationManager().authenticate(authenticationToken);
	}

	private Map<String, String> parseRequestJSON(HttpServletRequest request) {
		
		// JSON데이터를 분석해서 mid, mpw를 Map으로 처리
		try(Reader reader = new InputStreamReader(request.getInputStream())) {
			Gson gson = new Gson();
			
			// log.info("gson ====> " + gson);
			//log.info("reader ====> " + reader);
			// {mid=apiuser10, mpw=12345}
			return gson.fromJson(reader, Map.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}	
		return null;
	}
}
