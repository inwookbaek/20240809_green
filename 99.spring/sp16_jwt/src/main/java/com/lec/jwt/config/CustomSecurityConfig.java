package com.lec.jwt.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lec.jwt.security.APIUserDetailsService;
import com.lec.jwt.security.filter.APILoginFilter;
import com.lec.jwt.security.filter.RefreshTokenFilter;
import com.lec.jwt.security.filter.TokenCheckFilter;
import com.lec.jwt.security.handler.APILoginSuccessHandler;
import com.lec.jwt.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    // 주입
    private final APIUserDetailsService apiUserDetailsService;    
    private final JWTUtil jwtUtil;
    
	@Bean
	public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
	}
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	
    	log.info("------------web configure-------------------");
    	return (web) -> web.ignoring()
    			           .requestMatchers(PathRequest.toStaticResources()
    			        		                       .atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("------------configure-------------------");
        
// p785~787 - start ----------------------------------------------------------
        // AbstractAuthenticationProcessingFilter 설정
        // 이 필터는 login처리를 담당하기 때문에 경로와 실제인증처리를 담당하는
        // AuthenticationManager객체의 설정이 필수로 필요
        //AuthenticationManager설정
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(apiUserDetailsService).passwordEncoder(passwordEncoder());
        
        // Get AuthenticationManager
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        //반드시 필요
        http.authenticationManager(authenticationManager);

        // APILoginFilter
        APILoginFilter apiLoginFilter = new APILoginFilter("/generateToken");
        apiLoginFilter.setAuthenticationManager(authenticationManager);
       
        // P793
        //APILoginSuccessHandler
        //APILoginSuccessHandler successHandler = new APILoginSuccessHandler();
        APILoginSuccessHandler successHandler = new APILoginSuccessHandler(jwtUtil); // p806
        //SuccessHandler 세팅
        apiLoginFilter.setAuthenticationSuccessHandler(successHandler);
        
        //APILoginFilter의 위치 조정
        http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);
        
// p787~787 - end ----------------------------------------------------------
        

// p809 - start ----------------------------------------------------------
        //api로 시작하는 모든 경로는 TokenCheckFilter 동작
        http.addFilterBefore(
                // tokenCheckFilter(jwtUtil),
                tokenCheckFilter(jwtUtil, apiUserDetailsService), // p873
                UsernamePasswordAuthenticationFilter.class
        );
// p809 - end ----------------------------------------------------------
        
// p821 - start ----------------------------------------------------------
        //refreshToken 호출 처리
        http.addFilterBefore(new RefreshTokenFilter("/refreshToken", jwtUtil),
                TokenCheckFilter.class);
// p821 - end ----------------------------------------------------------
        
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
// p849 - start ----------------------------------------------------------
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });        
// p849 - 둥 ----------------------------------------------------------
        
        return http.build();
    }    

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

	private TokenCheckFilter tokenCheckFilter(JWTUtil jwtUtil
			, APIUserDetailsService apiUserDetailsService) {
        return new TokenCheckFilter(apiUserDetailsService, jwtUtil);
    }
	
}
