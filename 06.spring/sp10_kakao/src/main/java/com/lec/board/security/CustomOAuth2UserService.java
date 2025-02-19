package com.lec.board.security;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.lec.board.domain.Member;
import com.lec.board.domain.MemberRole;
import com.lec.board.dto.MemberSecurityDTO;
import com.lec.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
/*
	SSN(Kakao)로그인 연동후 이메일 구하기
	
	DefaultOAuth2UserService를 구현하기 전에 로그인을 하면 전달하는 정보가 UserDetails타입이
	아니기 때문에 에러가 발생한다. 이를 처리하려면 UserDetailsService를 구현하는 것 처럼
	OAuth2UserService인터페이스를 구현해야 한다. 
	
	이 자체를 구현할 수도 있지만 하위클래스인 DefaultOAuth2UserService을 상속해서 구현하는
	방식이 제일 간단하다.
*/

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

//	@Override
//	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		
//		log.info("==========> User Request : " + userRequest);		
//		log.info("OAuth2 User .............................");
//		
//		ClientRegistration clientRegistration = userRequest.getClientRegistration();
//		String clientName = clientRegistration.getClientName();
//		
//		log.info("1. Client Id = " + clientRegistration.getClientId());
//		log.info("2. Client Name = " + clientRegistration.getClientName());
//		log.info("3. Client Secret = " + clientRegistration.getClientSecret());
//		
//		OAuth2User oAuth2User = super.loadUser(userRequest);
//		Map<String, Object> paramMap = oAuth2User.getAttributes();
//		
//		// KAKAO 서비스의 경우 kakao_account라는 키로 접근하는 정보중에 email관련 정보가 있다.
//		paramMap.forEach((k,v) -> {
//			log.info("---------------------------------------------");
//			log.info(k + " = " + v);
//		});
//		
//		String email = null;
//
//		// SSN : google, naver, facebook등
//		switch(clientName) {
//		case "kakao": 
//			email = getKakoEmail(paramMap);
//			break;
//		case "gmail": 
//			email = getGmail(paramMap);
//			break;
//		case "naver": 
//			email = getNaver(paramMap);
//			break;
//		case "facebook": 
//			email = getFacebook(paramMap);
//			break;
//		}
//		
//		
//		return oAuth2User;
//		
//	}
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		log.info("==========> User Request : " + userRequest);		
		log.info("OAuth2 User .............................");
		
		ClientRegistration clientRegistration = userRequest.getClientRegistration();
		String clientName = clientRegistration.getClientName();		
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		Map<String, Object> paramMap = oAuth2User.getAttributes();		

		
		String email = null;
		switch(clientName) {
		case "kakao": 
			email = getKakoEmail(paramMap);
			break;
		}	
		
		email = "hong@hotmail.com";
				
		return generateDTO(email, paramMap);
	}
		
	private MemberSecurityDTO generateDTO(String email, Map<String, Object> paramMap) {
		
		Optional<Member> result = memberRepository.findByEmail(email);
		
		// 데이터베이스에 해당 이메일사용자가 없다면
		// 이미 가입한 회원은 기존정보를 리턴하고 새롭게 SSN로그인된 회원은
		// 자동으로 회원가입처리
		if(result.isEmpty()) {
			// 회원추가 : mid=이메일, mpw=12345
			Member member = Member.builder()
					.mid(email)
					.mpw(passwordEncoder.encode("12345"))
					.email(email)
					.social(true)
					.build();
			member.addRole(MemberRole.USER);
			memberRepository.save(member);
			
			MemberSecurityDTO memberSecurityDTO = 
				new MemberSecurityDTO(email, "12345", email, false, true, 
						Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
			memberSecurityDTO.setProps(paramMap);
			
			return memberSecurityDTO;
		} else {
			// SSN로그인한 회원과 일반가입 회원구분처리
			Member member = result.get();
			MemberSecurityDTO memberSecurityDTO
				= new MemberSecurityDTO(
						member.getMid(), 
						member.getMpw(), 
						member.getEmail(),
						member.isDel(),
						member.isSocial(),
						member.getRoleSet()
						      .stream()
						      .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
						      .collect(Collectors.toList()));

			return memberSecurityDTO;
		}
			
	}

	//  kakao_account = {
	//   profile_nickname_needs_agreement=false
	// , profile={nickname=홍길동, is_default_nickname=false}
	// }
	private String getKakoEmail(Map<String, Object> paramMap) {
		log.info("KAKAO ---------------------------------------------");
		
		Object value = paramMap.get("kakao_account");
		log.info(value);
		
		LinkedHashMap accountMap = (LinkedHashMap) value;
		String email = (String) accountMap.get("email");
		log.info("카카오 email = " + email);
		
		// kakao에서 동의항목에 email옵션이 활성화 되어 있지 않기 때문에 강제로 주입
		// return email;
		return "hong@hotmail.com";
	}
	
	private String getGmail(Map<String, Object> paramMap) {
		return null;
	}
	
	private String getNaver(Map<String, Object> paramMap) {
		return null;
	}
	
	private String getFacebook(Map<String, Object> paramMap) {
		return null;
	}
}










