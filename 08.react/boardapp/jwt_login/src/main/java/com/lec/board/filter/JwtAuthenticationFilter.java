package com.lec.board.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.lec.board.jwt.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/*
  클라이언트 요청 시 JWT 인증을 하기 위해 설치하는 커스텀 필터로, UsernamePasswordAuthenticationFilter 이전에 실행 할 것이다.
  클라이언트로부터 들어오는 요청에서 JWT 토큰을 처리하고, 유효한 토큰인 경우 해당 토큰의 인증 정보(Authentication)를 
  SecurityContext에 저장하여 인증된 요청을 처리할 수 있도록 한다.
  JWT를 통해 username + password 인증을 수행한다는 의미
*/
@Log4j2
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    
	private final JwtTokenProvider jwtTokenProvider;

	// doFilter()
	// resolveToken() 메서드를 사용하여 요청 헤더에서 JWT 토큰을 추출
    // JwtTokenProvider의 validateToken() 메서드로 JWT 토큰의 유효성 검증
	// 토큰이 유효하면 JwtTokenProvider의 getAuthentication() 메서드로 인증 객체 가져와서 SecurityContext에 저장
	// - 요청을 처리하는 동안 인증 정보가 유지된다
	// chain.doFilter()를 호출하여 다음 필터로 요청을 전달
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    		throws IOException, ServletException {
    	
    	log.info("1. ========> doFilter");
    	
        // 1. Request Header에서 JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) request);

        // 2. validateToken으로 토큰 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    // resolveToken() : Request Header에서 토큰 정보 추출
    // 주어진 HttpServletRequest에서 토큰 정보를 추출하는 역할
    // "Authorization" 헤더에서 "Bearer" 접두사로 시작하는 토큰을 추출하여 반환
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
