package com.lec.board.filter;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lec.board.provider.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

/*
	🔹 코드 상세 설명
	1️⃣ JWT 토큰 추출 (parseBearerToken())
	요청의 Authorization 헤더에서 "Bearer " 접두사를 검사하고, 그 뒤의 값을 반환
	헤더가 없거나 "Bearer "로 시작하지 않으면 null 반환
	2️⃣ JWT 토큰 검증 (jwtProvider.validate(token))
	JwtProvider를 사용해 토큰을 검증하고, 유효하면 이메일을 반환
	유효하지 않으면 null 반환하여 인증 실패
	3️⃣ Spring Security 인증 객체 생성
	AbstractAuthenticationToken authenticationToken =
	        new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
	        
	이메일을 기반으로 사용자를 인증하는 객체(UsernamePasswordAuthenticationToken)를 생성
	AuthorityUtils.NO_AUTHORITIES → 현재는 권한(Role)을 사용하지 않음 (필요 시 USER, ADMIN 등 추가 가능)
	
	4️⃣ SecurityContext에 인증 정보 저장
	
	SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	securityContext.setAuthentication(authenticationToken);
	SecurityContextHolder.setContext(securityContext);
	
	Spring Security에서 현재 요청이 인증되었음을 저장
	이후 컨트롤러에서 @AuthenticationPrincipal 또는 SecurityContextHolder.getContext().getAuthentication()을 사용해 사용자 정보를 가져올 수 있음
	
	🔹 요청 흐름 정리
	1️⃣ 클라이언트가 Authorization: Bearer <JWT> 헤더를 포함하여 요청
	2️⃣ JwtAuthenticationFilter가 요청을 가로채서 JWT 토큰을 추출
	3️⃣ JwtProvider를 사용해 토큰이 유효한지 확인
	4️⃣ 유효하면 SecurityContextHolder에 인증 정보를 저장
	5️⃣ 요청이 계속 진행되어 컨트롤러에서 인증된 사용자 정보를 사용할 수 있음
	
	✅ 최종 정리
	✔ JwtAuthenticationFilter는 모든 요청에서 JWT를 검사하는 필터
	✔ parseBearerToken() → HTTP 요청에서 JWT를 추출
	✔ jwtProvider.validate(token) → 토큰을 검증하여 이메일을 반환
	✔ 인증 성공 시 SecurityContextHolder에 저장하여 Spring Security에서 인증된 사용자로 인식
	✔ 컨트롤러에서 사용자 정보를 활용할 수 있음 (@AuthenticationPrincipal 활용 가능)
*/

/*
	🔹 JwtAuthenticationFilter의 역할
		클라이언트가 API 요청 시 JWT 토큰을 검사하여 인증을 수행하는 필터.
		OncePerRequestFilter를 상속받아 모든 요청마다 한 번만 실행.
		JWT 토큰이 유효하면 사용자를 인증하고, 유효하지 않으면 요청을 그대로 통과.

	🔹 JWT 인증 필터
	✅ 모든 요청에서 JWT 토큰을 검사하여 사용자 인증을 수행한다.
	✅ Spring Security의 SecurityContext에 인증 정보를 저장하여 인증된 사용자로 처리한다.
*/
@Log4j2
@Component  // Spring이 관리하는 Bean으로 등록
@RequiredArgsConstructor  // 생성자 주입을 자동으로 처리하는 Lombok 어노테이션
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider; // JWT 토큰을 검증하는 Provider 클래스

    /**
     * 📌 doFilterInternal 메서드: HTTP 요청이 들어올 때마다 실행되는 필터 메서드
     * @param request  HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param filterChain 필터 체인 (다음 필터로 요청을 넘기는 역할)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	    	
        try {
            // 1️⃣ 요청에서 JWT 토큰을 추출
            String token = parseBearerToken(request);
            
             // 2️⃣ 토큰이 없으면 요청을 그대로 다음 필터로 넘김
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 3️⃣ 토큰 검증 및 이메일 추출
            String email = jwtProvider.validate(token);

            // 4️⃣ 검증에 실패하면 요청을 그대로 다음 필터로 넘김
            if (email == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 5️⃣ 인증 객체 생성 (사용자의 권한 정보를 설정할 수도 있음)
            AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);

            // 6️⃣ 요청의 세부 정보를 설정 (예: IP 주소, 세션 정보 등)
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 7️⃣ SecurityContext에 인증 정보 저장 (인증된 사용자로 인식됨)
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);
            
            SecurityContextHolder.setContext(securityContext);

        } catch (Exception exception) {
            // 예외 발생 시 오류 로그 출력 (개발 중 디버깅용)
            exception.printStackTrace();
        }
        
        // 8️⃣ 요청을 다음 필터로 넘김
        filterChain.doFilter(request, response);
    }

    /**
     * 📌 parseBearerToken 메서드: HTTP 요청에서 Authorization 헤더를 분석하여 JWT 토큰을 추출
     * @param request HTTP 요청 객체
     * @return JWT 토큰 (없으면 null)
     */
    private String parseBearerToken(HttpServletRequest request) {
        // 1️⃣ Authorization 헤더 값 가져오기
        String authorization = request.getHeader("Authorization");
        log.info(" =====> authorization : " + authorization);
        
        // 2️⃣ Authorization 헤더가 존재하는지 확인
        boolean hasAuthorization = StringUtils.hasText(authorization);
        log.info(" =====> hasAuthorization : " + hasAuthorization);
        if (!hasAuthorization) return null;

        // 3️⃣ 헤더 값이 "Bearer "로 시작하는지 확인
        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;
        log.info(" =====> hasAuthorization : " + hasAuthorization);

        // 4️⃣ "Bearer " 이후의 문자열(토큰) 추출
        String token = authorization.substring(7);
        log.info(" =====> token : " + token);
        
        return token;
    }
}
