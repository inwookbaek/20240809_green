package com.lec.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lec.board.filter.LoginFilter;

/*
	이 설정은 Spring Security 6.x 버전에서 JWT 기반 인증 방식을 사용할 때의 설정입니다.

	🔹 주요 개념 설명
	1. @EnableWebSecurity
	Spring Security를 활성화하고, 개발자가 직접 설정할 수 있도록 해줍니다.
	Spring Security 6.x 버전에서는 생략 가능 (SecurityFilterChain을 등록하면 자동 적용됨).
	
	2. AuthenticationManager
	로그인 시 입력된 사용자 정보를 인증하는 역할을 합니다.
	이 설정을 통해 LoginFilter에서 인증 매니저를 사용할 수 있습니다.
	
	3. BCryptPasswordEncoder
	비밀번호를 안전하게 저장하기 위해 암호화하는 방식.
	bCryptPasswordEncoder.encode("비밀번호")로 비밀번호를 암호화할 수 있음.
	
	4. SecurityFilterChain
	Spring Security에서 요청을 처리하는 필터 체인을 정의하는 부분.
	HttpSecurity 객체를 통해 요청 인증, 권한 부여, 보안 필터 등을 설정.
	
	5. csrf().disable()
	CSRF(크로스 사이트 요청 위조) 보호 기능을 비활성화.
	JWT 기반 인증 방식에서는 필요하지 않음.
	
	6. formLogin().disable() & httpBasic().disable()
	기본 로그인 폼과 HTTP 기본 인증을 사용하지 않음.
	JWT 방식으로 인증을 처리하기 때문에 불필요.
	
	7. authorizeHttpRequests()
	특정 URL에 대한 접근 권한을 설정.
	permitAll(): 모든 사용자 접근 가능.
	hasRole("ADMIN"): ADMIN 역할을 가진 사용자만 접근 가능.
	authenticated(): 인증된 사용자만 접근 가능.
	
	8. LoginFilter 추가
	UsernamePasswordAuthenticationFilter 위치에 LoginFilter를 추가.
	사용자가 로그인할 때 JWT를 생성하고 응답하는 역할을 함.
	
	9. sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	Spring Security의 세션 정책을 **STATELESS(무상태)**로 설정.
	JWT 기반 인증에서는 세션을 사용하지 않으므로 비활성화.
	
	🔹 결론
	✅ Spring Security 6.x 버전에서는 @EnableWebSecurity를 생략할 수 있음
	✅ JWT 기반 인증 방식에서는 세션을 사용하지 않기 때문에 STATELESS 설정 필수
	✅ 기본 로그인 폼과 HTTP 기본 인증을 비활성화하고, LoginFilter를 통해 인증 처리
	
	이제 Spring Security의 동작 원리를 초보자도 쉽게 이해할 수 있습니다! 🚀
*/

@Configuration  // 이 클래스가 설정 클래스임을 나타냄
@EnableWebSecurity  // Spring Security 설정을 커스터마이징 (Spring Security 6.x에서는 생략 가능)
public class SecurityConfig {
	
   





    // 인증 관련 설정을 주입받는 생성자 (DI: Dependency Injection)
    private final AuthenticationConfiguration authenticationConfiguration;
    
    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }
    
    /**
     * 📌 인증 관리자 (AuthenticationManager) Bean 등록
     * - Spring Security에서 인증을 처리하는 핵심 컴포넌트
     * - 사용자의 아이디와 비밀번호를 검증하는 역할
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    /**
     * 📌 비밀번호 암호화 설정 (BCrypt 사용)
     * - 사용자 비밀번호를 보안 처리하여 저장하기 위해 사용
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 📌 보안 필터 체인(SecurityFilterChain) 설정
     * - HTTP 요청에 대한 보안 정책을 정의
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            /**
             * 📌 CSRF 보호 비활성화
             * - JWT 기반 인증에서는 CSRF 토큰이 필요하지 않으므로 비활성화
             */
            .csrf((csrf) -> csrf.disable())

            /**
             * 📌 기본 로그인 폼(formLogin)과 HTTP 기본 인증(httpBasic) 비활성화
             * - JWT 인증 방식을 사용하므로 불필요
             */
            .formLogin((login) -> login.disable())
            .httpBasic((basic) -> basic.disable())

            /**
             * 📌 URL 접근 권한 설정
             * - "/", "/login", "/join" → 누구나 접근 가능 (permitAll)
             * - "/admin" → ADMIN 권한을 가진 사용자만 접근 가능 (hasRole("ADMIN"))
             * - 그 외 모든 요청은 인증된 사용자만 접근 가능 (authenticated)
             */
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/join").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated())

            /**
             * 📌 사용자 로그인 요청을 처리하는 커스텀 필터 추가
             * - LoginFilter를 UsernamePasswordAuthenticationFilter 위치에 추가
             * - JWT 기반 로그인 처리를 담당
             */
            // 필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
            .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration)), 
            		UsernamePasswordAuthenticationFilter.class)

            /**
             * 📌 세션 관리 정책 설정
             * - JWT 인증 방식을 사용하므로 **세션을 사용하지 않도록 설정 (STATELESS)**
             */
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
