package com.lec.board.filter;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
  	📌 사용자 로그인 요청을 처리하는 커스텀 필터 (LoginFilter)
  	- 사용자의 아이디(username)와 비밀번호(password)를 검증하는 역할
  	- 인증이 성공하면 `successfulAuthentication()` 메서드 실행
  	- 인증이 실패하면 `unsuccessfulAuthentication()` 메서드 실행
  
  	🔹 LoginFilter 동작 과정
	1️⃣ 사용자가 로그인 요청을 보냄 (예: POST /loginProc)

	요청 본문:
	{
	    "username": "user1",
	    "password": "password123"
	}
	
	2️⃣ attemptAuthentication() 실행
	
	username과 password를 추출하여 UsernamePasswordAuthenticationToken 생성
	AuthenticationManager를 사용하여 인증 시도
	
	3️⃣ 인증 성공 시 (successfulAuthentication())
	
	로그를 남기고 추가적인 작업(예: JWT 토큰 발급) 가능
	
	4️⃣ 인증 실패 시 (unsuccessfulAuthentication())
	
	로그를 남기고 응답 상태 코드 401 Unauthorized 반환 가능
	
	🔹 결론
	✅ Spring Security의 UsernamePasswordAuthenticationFilter를 상속받아 로그인 필터를 직접 구현
	✅ 사용자가 입력한 username과 password를 이용해 AuthenticationManager로 인증을 수행
	✅ 로그인 성공 시 successfulAuthentication()에서 JWT 토큰을 발급하여 응답 가능
	✅ 로그인 실패 시 unsuccessfulAuthentication()에서 실패 로그 및 응답 처리 가능
	
	이제 LoginFilter의 동작 방식이 쉽게 이해될 것! 🚀
 */
@Log4j2  // 로그를 출력하기 위한 어노테이션 (Lombok 사용)
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    /**
     * 📌 LoginFilter 생성자
     * - AuthenticationManager를 주입받아 인증을 수행할 수 있도록 설정
     */
    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 📌 로그인 요청이 들어왔을 때 실행되는 메서드
     * - 사용자의 아이디(username)와 비밀번호(password)를 받아서 인증 요청을 수행
     * - Spring Security의 `UsernamePasswordAuthenticationToken`을 사용하여 인증 시도
     * - 인증이 성공하면 `Authentication` 객체가 반환됨
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        // 1️⃣ 클라이언트가 입력한 username과 password 가져오기
        String username = obtainUsername(request);  // 기본적으로 request.getParameter("username")와 동일
        String password = obtainPassword(request);  // 기본적으로 request.getParameter("password")와 동일

        // 2️⃣ 가져온 값을 기반으로 인증 객체(UsernamePasswordAuthenticationToken) 생성
        //    스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(username, password);

        log.info("📌 로그인 시도 - username = {}, password = {}, token = {}", username, password, authToken.getName());

        // 3️⃣ 인증 시도 (AuthenticationManager를 통해 인증 진행)
        //    token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    /**
     * 📌 로그인 성공 시 실행되는 메서드
     * - 인증이 성공하면 사용자의 정보를 기반으로 추가적인 작업 수행 가능
     * - 예: JWT 토큰을 생성하여 클라이언트에게 반환하는 로직 추가 가능
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        log.info("✅ 로그인 성공 - 사용자: {}", authResult.getName());
        
        // 🎯 여기에 JWT 토큰을 생성하여 응답 헤더에 추가하는 로직을 구현하면 됨
        // response.addHeader("Authorization", "Bearer " + jwtToken);
    }

    /**
     * 📌 로그인 실패 시 실행되는 메서드
     * - 인증이 실패하면 사용자의 요청을 처리하는 로직 추가 가능
     * - 예: 실패 메시지를 JSON으로 응답하거나, 특정 페이지로 리디렉션
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        log.warn("❌ 로그인 실패 - 이유: {}", failed.getMessage());

        // 🎯 로그인 실패 시 응답 상태 코드 401 (Unauthorized) 설정 가능
        // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // response.getWriter().write("로그인 실패: " + failed.getMessage());
    }
}
