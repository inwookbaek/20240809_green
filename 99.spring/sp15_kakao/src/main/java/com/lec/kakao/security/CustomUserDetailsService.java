package com.lec.kakao.security;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lec.kakao.domain.Member;
import com.lec.kakao.repository.MemberRepository;
import com.lec.kakao.security.dto.MemberSecurityDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
// p728
@RequiredArgsConstructor // 추가
public class CustomUserDetailsService implements UserDetailsService {

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
	
	// p728에서 주석처리
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    	log.info("loadUserByUsername: " + username);
//    	
//    	UserDetails userDetails 
//    		= User.builder()
//    			  .username("user1")
//    			  // .password("12345")
//    			  // p690
//    			  .password(passwordEncoder.encode("12345"))
//    			  .authorities("ROLE_USER")
//    			  .build();
//    	return userDetails;
//    }
    
    // p689, p728에서 주석처리
//    private PasswordEncoder passwordEncoder;
//    public CustomUserDetailsService() {
//    	this.passwordEncoder = new BCryptPasswordEncoder();
//    }
    
	
	// p728
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("loadUserByUsername: " + username);
		
		Optional<Member> result = memberRepository.getWithRoles(username);
		
		if(result.isEmpty()) { // 해당 아이디를 가진 사용자가 없을 경우
			throw new UsernameNotFoundException("username not found!!!");
		}
		
		Member member = result.get();
		
		MemberSecurityDTO memberSecurityDTO = 
			new MemberSecurityDTO(
				member.getMid(),
				member.getMpw(),
				member.getEmail(),
				member.isDel(),
				false,
				member.getRoleSet()
				      .stream()
				      .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
				      .collect(Collectors.toList())
			);
		
		log.info("memberSecurityDTO");
		log.info(memberSecurityDTO);
		
		return memberSecurityDTO;
	}
}
