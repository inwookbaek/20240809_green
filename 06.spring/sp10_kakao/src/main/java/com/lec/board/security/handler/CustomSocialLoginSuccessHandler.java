package com.lec.board.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.lec.board.dto.MemberSecurityDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/*
	스프링시큐리티는 로그인 성공과 실패를 커스터마이징할 수 있도록 AuthenticationSuccessHandler와
	AuthenticationFaileHandler인터페이스를 제공한다.
	
	실습에서 소셜로그인성공한 후에 특정페이지로 이동하는 방법을 처리해야 하는데 AuthenticationSuccessHandler를
	이용해서 처리하도록 한다.
*/
@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {

	private final PasswordEncoder passwordEncoder;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("-----------------------------------------------");
		log.info("CustomSocialLoginSuccessHandler.onAuthenticationSuccess() 호출................");
		log.info(authentication.getPrincipal());
		
		MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
		
		String encodePw = memberSecurityDTO.getMpw();
		
		// 소셜로그인이고 패스워드가 12345
		if(memberSecurityDTO.isSocial() &&
			(memberSecurityDTO.getMpw().equals("12345") 
				|| passwordEncoder.matches("12345", memberSecurityDTO.getMpw()))) {
			log.info("비밀번호를 변경해야 합니다!!");
			log.info("회원정보수정(비밀번호수정)");
			response.sendRedirect("/member/modify");
		} else {
			response.sendRedirect("/board/list");
		}
		
		
	}

}
