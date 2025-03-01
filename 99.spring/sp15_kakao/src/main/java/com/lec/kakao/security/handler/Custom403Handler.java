package com.lec.kakao.security.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

// p716
// 403에러의 종류가 많기 때문에 여러 에러 페이지를 보여주는 대신에 AccessDeniedHandler를 구현해서
// 상황에 맞게 처리하도록 로직 작성. Custom403Handler가 동작하기 위해서 스프링시큐리티 설정을 담당하는
// CustomerSecurityConfig에 Bean과 예외처리를 지정해야 한다.
@Log4j2
public class Custom403Handler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.info("--------ACCESS DENIED--------------");

        response.setStatus(HttpStatus.FORBIDDEN.value());

        //JSON 요청이었는지 확인
        String contentType = request.getHeader("Content-Type");
        boolean jsonRequest = contentType.startsWith("application/json");

        log.info("isJOSN: " + jsonRequest);

        //일반 request
        if (!jsonRequest) {
            response.sendRedirect("/member/login?error=ACCESS_DENIED");
        }
    }
}
