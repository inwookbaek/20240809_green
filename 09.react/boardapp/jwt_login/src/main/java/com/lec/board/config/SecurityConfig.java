package com.lec.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.lec.board.filter.JwtAuthenticationFilter;
import com.lec.board.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  
    private final JwtTokenProvider jwtTokenProvider;
    
 // CORS 설정 추가
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);  // 쿠키 인증 요청 허용
        config.addAllowedOriginPattern("*"); // 모든 도메인 허용
        config.addAllowedHeader("*");  // 모든 헤더 허용
        config.addAllowedMethod("*");  // 모든 HTTP 메서드 허용
        source.registerCorsConfiguration("/**", config);
        
        log.info("1. ========> corsFilter");
        
        return new CorsFilter(source);
    }    
    

    // filterChain() ➡︎ HttpSecurity를 구성하여 보안 설정을 정의
    // .httpBasic(http -> http.disable())  : Basic 인증을 사용하지 않음
    // .csrf(csrf -> csrf.disable())       : CSRF(Cross-Site Request Forgery) 보안을 비활성화
    // .sessionManagement()                : JWT를 사용하기 때문에 세션을 사용하지 않음
    // .authorizeHttpRequests()            : 요청에 대한 인가 규칙 설정
    //   .requestmatchers("members/sign-in").permitAll()  : "members/sign-in" 경로에 대한 요청은 모든 사용자에게 허용
    //   .requestmatchers("members/test").hasRole("USER") : "members/test" 경로에 대한 요청은 "USER" 권한을 가진 사용자만 허용
    //   .anyRequest().authenticated()                    : 나머지 모든 요청은 인증을 필요로 함
    // .addFilterBefore()                                 : JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 앞에 추가하여 JWT 인증을 처리
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	
    	log.info("2. ========> filterChain");
    	
        return httpSecurity
        		.httpBasic(http -> http.disable()) // 기본 HTTP Basic 인증 비활성화 (JWT 사용 시 필요 없음, REST API이므로 basic보안을 사용하지 않음)
        		.csrf(csrf -> csrf.disable())      // CSRF 보호 비활성화 (JWT 사용 시 필요 없음)
                .sessionManagement(
                	session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 사용하지 않음 (JWT 인증 방식이므로)
                .authorizeHttpRequests(
                	auth -> auth.requestMatchers("/members/login", "/board/list").permitAll()  // 로그인 API는 인증 없이 접근 가능(모든 요청을 허가)
                                .requestMatchers("/members/test").hasRole("USER")              // USER 권한이 있어야 요청 가능
                                .anyRequest().authenticated())                                 // 그 외 모든 요청은 인증 필요
                // JWT 인증 필터 추가 : JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class) 
                .build();
    }
    
    // AuthenticationManager Bean 등록 (Spring Security 6 방식)
    // Spring Security 6에서는 AuthenticationManager를 직접 빈으로 등록해야 함
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
    		throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    // passwordEncoder() : 비밀번호 암호화 설정 (BCryptEncoder)
    // DelegatingPasswordEncoder를 생성하여 반환
    // DelegatingPasswordEncoder는 여러 암호화 알고리즘을 지원하며, Spring Security의 기본 권장 알고리즘을 사용하여 비밀번호를 인코딩
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt Encoder 사용
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}