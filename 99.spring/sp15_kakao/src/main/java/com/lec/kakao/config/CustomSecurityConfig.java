package com.lec.kakao.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.lec.kakao.security.CustomUserDetailsService;
import com.lec.kakao.security.handler.Custom403Handler;
import com.lec.kakao.security.handler.CustomSocialLoginSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
//p691 - 어노테이션을 이용해서 권한을 설정하려면 @EnableGlobalMethodSecurity를 추가
//@EnableGlobalMethodSecurity는 디프리케이트 되어 @@EnableMethodSecurity를 사용
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

	// p704 주입필요
	private final DataSource dataSource;
	private final CustomUserDetailsService userDetailsService;	
	
	// p689
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// p680 - filterChain메서드가 동작하면 로그인/암호 화면 없이 /board/list에 바로 접근할 수 있다.
	// 모든 사용자가 모든 경로에 접근 가능
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        log.info("------------configure-------------------");
//        return http.build();
//    }
	
	// p684 - 인증처리를 위한 UserSetailsService	
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        log.info("------------configure-------------------");
//        
//        // http.formLogin();
//        // http.formLogin().loginPage("/member/login");
//        // p694
//        // 이 시점에서 login.html에서 POST방식(MemberController)이 지정되지 않았기 때문에
//        // 로그인을 해도 login.html이 나타난다. 로그인성공, 로그인실패등 다른 뷰가 나오게 정의 할 수 있다.
//        // 로그인, 로그아웃을 스프링시큐ㅣ티가 처리할 수 있도록 구성할 수도 있다.
//        // 커스텀로그인 페이지
//        http.formLogin(login -> login.loginPage("/member/login"));
//        
//        // p699 -CSRF 토큰 비활성화
//        http.csrf(csrf -> csrf.disable());
//
//        // p704 - remember-me 기능설정
//        // 스프링시큐리티의 `remember-me`기능은 쿠키를 이용해서 브라우저에 로그인했던 정보를 유지하기
//        // 때문에 매버누로그인을 실행할 필요가 없어진다.
//        // remember-me는 쿠키에 유효시간을 지정해서 쿠키를 브라우저가 보관하ㅔ 하고 쿠키의 값인 특정
//        // 문자열을 보관시켜서 로그인 관련정보를 유지하는 방식
//        // 쿠키값을 보관하기 위해 여러가지 방법이 있겠지만 가장 무난한 방식이 DB에 저장하는 방법
//        // 테이블은 persistent_logins을 사용하는데 스프링시큐리티내부에서 사용하기 때문에 변경하지 말아야 한다.
////        create table persistent_logins (
////        		username varchar(64) not null,
////        		series varchar(64) primary key,
////        		token varchar(64) not null,
////        		last_used timestamp not null
////        )
//        http.rememberMe(me -> me
//                .key("12345678")
//                .tokenRepository(persistentTokenRepository())
//                .userDetailsService(userDetailsService)
//                .tokenValiditySeconds(60*60*24*30));
//        
//        // http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()); // depricated 403
//        http.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler())); //403
//        
//        return http.build();
//    }
    
    
    // 683 정적자원(css, js)들은 스프링시큐리티 적용에서 제외
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	
    	log.info("------------web configure-------------------");
    	return (web) -> web.ignoring()
    					   .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    
 // p704
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    } 
    
    // p718
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
    	return new Custom403Handler();
    }    
    
	// p743 - OAuth2 Client 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("------------configure-------------------");

        //커스텀 로그인 페이지
        // http.formLogin().loginPage("/member/login");
        http.formLogin(login -> login.loginPage("/member/login"));
        //CSRF 토큰 비활성화
       //  http.csrf().disable();
        http.csrf(csrf -> csrf.disable());
        
        http.rememberMe(me -> me  
		    .key("12345678")
		    .tokenRepository(persistentTokenRepository())
		    .userDetailsService(userDetailsService)
		    .tokenValiditySeconds(60*60*24*30));        
        
        // p746
        // http.oauth2Login(login -> login.loginPage("/member/login"));

        // p761
        http.oauth2Login(login -> login.loginPage("/member/login")
        		                       .successHandler(authenticationSuccessHandler()));
        
        return http.build();
    }    
    
	// p760 - OAuth로그인 관련해서 CustomSocialLoginSuccessHandler를 
    // 로그인 성공시 이용하도록 하는 부분
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }    
}
