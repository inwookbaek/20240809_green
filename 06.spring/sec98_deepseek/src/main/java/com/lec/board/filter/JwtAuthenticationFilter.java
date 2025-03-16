package com.lec.board.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lec.board.service.CustomUserDetailsService;
import com.lec.board.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
//이 클래스를 Spring의 컴포넌트로 등록합니다. (Spring이 관리하는 빈)
@Component 
//JwtAuthenticationFilter는 OncePerRequestFilter를 상속받아, 요청마다 한 번만 실행되는 필터
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	// JWT 관련 작업을 처리하는 서비스 클래스
	private final JwtService jwtService;

	 // 사용자 정보를 로드하는 서비스 클래스
	 private final CustomUserDetailsService userDetailsService;
	
	 // 생성자를 통해 JwtService와 CustomUserDetailsService를 주입받습니다. (의존성 주입)
	 public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
		 
		 log.info("JwtAuthenticationFilter ==> " + userDetailsService.toString());
		 
	     this.jwtService = jwtService;
	     this.userDetailsService = userDetailsService;
	 }

	 // 필터의 주요 로직을 처리하는 메서드입니다.
	 @Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	         throws ServletException, IOException {
	
	     // 요청에서 JWT 토큰을 추출합니다.
	     String token = extractToken(request);
	
	     // 토큰이 존재하고 유효한 경우
	     if (token != null && jwtService.validateToken(token)) {
	         // 토큰에서 사용자 이름(또는 이메일)을 추출합니다.
	         String username = jwtService.getUsernameFromToken(token);
	
	         // 사용자 이름을 기반으로 사용자 정보를 데이터베이스에서 로드합니다.
	         UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	
	         // 사용자 정보를 기반으로 인증 객체를 생성합니다.
	         UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	                 userDetails, // 사용자 정보
	                 null, // 비밀번호 (여기서는 사용하지 않으므로 null)
	                 userDetails.getAuthorities() // 사용자의 권한 정보
	         );
	
	         // 인증 객체에 요청 정보를 추가합니다.
	         authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	
	         // SecurityContext에 인증 객체를 저장합니다. (인증 완료)
	         SecurityContextHolder.getContext().setAuthentication(authentication);
	     }
	
	     // 다음 필터로 요청과 응답을 전달합니다.
	     filterChain.doFilter(request, response);
	 }
	
	 // 요청 헤더에서 JWT 토큰을 추출하는 메서드입니다.
	 private String extractToken(HttpServletRequest request) {
	     // "Authorization" 헤더에서 토큰을 가져옵니다.
	     String bearerToken = request.getHeader("Authorization");
	     
	     
	
	     // 헤더가 존재하고 "Bearer "로 시작하는 경우
	     if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	         // "Bearer "를 제외한 실제 토큰 부분을 반환합니다.
	         return bearerToken.substring(7);
	     }
	
	     // 토큰이 없거나 형식이 잘못된 경우 null을 반환합니다.
	     return null;
	 }
}