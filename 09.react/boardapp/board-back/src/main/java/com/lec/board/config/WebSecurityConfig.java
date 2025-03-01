package com.lec.board.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lec.board.filter.JwtAuthenticationFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/*
	🔹 코드의 역할
	1️⃣ Spring Security 설정 (WebSecurityConfig)
	
	JWT 기반의 Stateless 인증 방식을 설정
	특정 API 경로는 인증 없이 접근 가능하도록 허용
	나머지 요청은 JWT를 통한 인증 필요
	JWT 필터(JwtAuthenticationFilter)를 Spring Security 필터 체인에 추가
	인증되지 않은 요청이 발생하면 FailedAuthenticationEntryPoint를 통해 JSON 응답 반환
	2️⃣ 인증 예외 처리 (FailedAuthenticationEntryPoint)
	
	인증되지 않은 사용자가 보호된 API에 접근하면 403 Forbidden 응답 반환
	JSON 형식의 메시지로 "Do not have permission." 제공
*/

@Log4j2
@Configurable
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfig corsConfig;
    
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity, HttpMethod method) throws Exception {
    	
    	
    	log.info(" =====> WebSecurityConfig.configure : " + method);
    	  	
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfig())) // 🔹 CORS 활성화 (CorsConfig 적용)
                .formLogin(login -> login.disable()) // 🔹 폼 로그인 비활성화
                .csrf(csrf -> csrf.disable())  // 🔹 CSRF 비활성화 (JWT 인증에서는 불필요)
                .httpBasic(httpBasic -> httpBasic.disable()) // 🔹 기본 HTTP 인증 방식 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔹 세션 관리 (Stateless)            
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers(HttpMethod.POST, "/api/v1/auth/hello").permitAll()
                        .requestMatchers("/", "/api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/board/**", "/api/v1/user/*").permitAll()
                		.anyRequest().authenticated())     // 🔹 요청별 인증 및 권한 설정 	             
                .exceptionHandling(e -> e.authenticationEntryPoint(new FailedAuthenticationEntryPoint())) // 🔹 인증 예외 발생 시 JSON 응답 반환
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // 🔹 JWT 필터 적용
                .build(); // 🔹 설정 적용
    }

    /**
     * 📌 CORS 정책 설정
     * @return CorsConfigurationSource 객체
     */
    @Bean
    public CorsConfigurationSource corsConfig() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // 모든 도메인 허용
        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용 (GET, POST, PUT, DELETE 등)
        configuration.addAllowedHeader("*"); // 모든 헤더 허용
        configuration.setAllowCredentials(true); // 쿠키 허용 (JWT 사용 시 true)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

/**
 * 🔹 인증 실패 처리 클래스
 * ✅ 인증되지 않은 사용자가 보호된 API에 접근하면 JSON 응답 반환
 * 200 
 * - 성공                 : "SU" / "Success"
 * 400
 * - 유효성 검증 실패     : "VF" / "Validate failed"
 * - 중복된 이메일        : "DE" / "Duplicate email"
 * - 중복된 전화번호      : "DT" / "Duplicate Telephone"
 * - 중복된 닉네임        : "DN" / "Duplicate Nickname"
 * - 존재하지 않는 유저   : "NU" / "This user dose not exit."
 * - 존재하지 않는 게시물 : "NB" / "This board does not exit."
 * 401
 * - 로그인실패           : "SF" / "Login information mismatch"
 * - 인증실패             : "AF" / "AUthorization Failed!!"
 * 403
 * - 권한 없음            : "NP" / "Do not have permission!!"
 * 500
 * - 데이터베이스에러     : "DBE" / "Database Error"
 * 
 */
class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
    	
    	System.out.println("=======> " + HttpServletResponse.SC_UNAUTHORIZED);
    	
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{ \"code\": \"AF\", \"message\": \"AUthorization Failed!!\" }");
    }
}
