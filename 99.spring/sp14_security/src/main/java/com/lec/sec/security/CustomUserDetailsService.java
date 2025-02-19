package com.lec.sec.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
// @RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private PasswordEncoder passwordEncoder;

	public CustomUserDetailsService() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	// p686 - UserDetails
	// 스프링시큐리티는 내부적으로 UserDetails타입의 객체를 이용해서 패스워드를 검사
	// UserDetails 인터페이스의 추상메서드 getAuthoritues, getPassword등이 있다.
	// 이중 getAuthoritues는 사용자가 가진 모든 인가(Athority)정보를 반환해야 한다.
	// 개발단계에서 
	// 1. UserDetails타입에 맞는 객체가 필요하고
	// 2. 이를 CustomUserDetailsService에 반환하는 작업이 필요
	// 스프링시큐리티API에는 UserDetails인터페이스를 구현한 User클래스를 제공한다.
	// User 클래스는 builder방식을 지원
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    	 log.info("loadUserByUsername: " + username);
//    	 return null;
//    }
	
	// p687
	// localhost:8080/login 화면 접속후 user/pass를 입력하면 PasswordEncoder에러가 발생
	// 스프링시큐리티는 기본적으로 PasswordEncoder가 필요 하다.
	// PasswordEncoder역시 인터페이스로 제공하는데 이를 구현하거나 스프링시큐리티API에서
	// 제공하는 클래스를 지정할 수 있다. 여러 PasswordEncoder타입의 클래스중에서 가장 무난한 것이
	// BCryptPasswordEncoder라는 클래스이다. 	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("loadUserByUserName = " + username);
		
		UserDetails userDetails = User.builder()
									  .username("user1")
									  //.password("12345")   // PasswordEncoder가 없어서 에러 발생
									  //.password("{noop}12345")   // PasswordEncoder사용하지 않을 경우 {noop}를 prefix로 정의
									  // p690
									  .password(passwordEncoder.encode("12345"))
									  .authorities("ROLE_USER")
									  .build();
		
		return userDetails;
	}

}
