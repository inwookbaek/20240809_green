package com.lec.kakao.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.lec.kakao.security.dto.MemberSecurityDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// p758 - AuthenticationSuccessHandler를 이용한 로그인 후처리
// 스프링시큐리티는 로그인 성공과 실패를 커스터마이징할 수 있도록 AuthenticationSuccessHandler와
// AuthenticationFaileHandler 인터페이스를 제공. 실습은 소셜로그인성공후에 현재 사용자의 패스워드에
// 따라서 특정 페이지로 이동하는 방법을 처리해야 하는데 AuthenticationSuccessHandler를 이용해서 처리
// 패스워드가 1111로 처리된 경우의 로직 만들기
@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
    		throws IOException, ServletException {

        log.info("----------------------------------------------------------");
        log.info("CustomLoginSuccessHandler onAuthenticationSuccess ..........");
        log.info(authentication.getPrincipal());

        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();

        String encodedPw = memberSecurityDTO.getMpw();

        //소셜로그인이고 회원의 패스워드가 1111이라면
        if (memberSecurityDTO.isSocial()
                && (memberSecurityDTO.getMpw().equals("1111")
                    ||  passwordEncoder.matches("1111", memberSecurityDTO.getMpw())
        )) {
            log.info("Should Change Password");

            log.info("Redirect to Member Modify ");
            response.sendRedirect("/member/modify");

            return;
        } else {

            response.sendRedirect("/board/list");
        }
    }
}
