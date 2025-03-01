package com.lec.kakao.security;

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

import com.lec.kakao.domain.Member;
import com.lec.kakao.domain.MemberRole;
import com.lec.kakao.repository.MemberRepository;
import com.lec.kakao.security.dto.MemberSecurityDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
// p748 -  로그인연동후 이메일 구하기
// DefaultOAuth2UserService를 현하기전에 로그인을 하면 전달하는 정보가 UserDetails타입이 아니기
// 때문에 에러가 발생한다. 이를 처리하려면 UserDetailsService인터페이스를 구현하듯이 OAuth2UserSevice
// 인터페이스를 구현해야 한다. 이 자체를 구현할 수도 있지만 하위클래스인 DefaultOAuth2UserService를
// 상속해서 구현하는 방식이 가장 간단하다.
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	// p749
	// loadUser()의 리턴타입은 OAuth2User로 이전의 코드를 작성후에 로그인처리가 되면 log메시지가 출력된다.
//	  @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//
//        log.info("userRequest....");
//        log.info(userRequest);
//
//        return super.loadUser(userRequest);
//    }
  
	 // p750 - kakao
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//
//        log.info("userRequest....");
//        log.info(userRequest);
//
//        log.info("oauth2 user.....................................");
//
//        ClientRegistration clientRegistration = userRequest.getClientRegistration();
//        String clientName = clientRegistration.getClientName();
//
//        log.info("NAME: "+clientName);
//        
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        Map<String, Object> paramMap = oAuth2User.getAttributes();
//
//        paramMap.forEach((k, v) -> {
//        	log.info("---------------------------------------------");
//        	log.info(k + ":=" + v);
//        });
//
//        return oAuth2User;
//    }
	
    // 751 - google, facebook 등 다양한 소셜로그인 적용
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//    	
//    	log.info("userRequest....");
//    	log.info(userRequest);
//    	
//    	log.info("oauth2 user.....................................");
//    	
//    	ClientRegistration clientRegistration = userRequest.getClientRegistration();
//    	String clientName = clientRegistration.getClientName();
//    	
//    	log.info("NAME: "+clientName);
//    	
//    	OAuth2User oAuth2User = super.loadUser(userRequest);
//    	Map<String, Object> paramMap = oAuth2User.getAttributes();
//    	
//        String email = null;
//
//        switch (clientName){
//            case "kakao":
//                email = getKakaoEmail(paramMap);
//                break;
//        }
//
//        log.info("===============================");
//        log.info(email);
//        log.info("===============================");
//
//        return oAuth2User;
//    }
    
    // p755
    // 카카오에서 얻어온 이메일을 이용해 같은 이메일을 가진 사용자를 검색후
    // 없는 경우에는 자동으로 회원가입을 하고 MemberSecurityDTO를 반환하도록 구성
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("userRequest....");
        log.info(userRequest);

        log.info("oauth2 user.....................................");

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("NAME: "+clientName);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        switch (clientName){
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }

        // kakao에서 동의항목에 email 옵션이 활성화되지 않아 강제로 주입
        email = "iwbaek@gmail.com";
        
        log.info("===============================");
        log.info(email);
        log.info("===============================");

        return generateDTO(email, paramMap);
    }
    
    
    private MemberSecurityDTO generateDTO(String email, Map<String, Object> params){

        Optional<Member> result = memberRepository.findByEmail(email);

        //데이터베이스에 해당 이메일을 사용자가 없다면
        if(result.isEmpty()){
            //회원 추가 -- mid는 이메일 주소/ 패스워드는 1111
            Member member = Member.builder()
                    .mid(email)
                    .mpw(passwordEncoder.encode("1111"))
                    .email(email)
                    .social(true)
                    .build();
            member.addRole(MemberRole.USER);
            memberRepository.save(member);

            //MemberSecurityDTO 구성 및 반환
            MemberSecurityDTO memberSecurityDTO =
                    new MemberSecurityDTO(email, "1111",email,false, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
            memberSecurityDTO.setProps(params);

            return memberSecurityDTO;
        } else {
            Member member = result.get();
            MemberSecurityDTO memberSecurityDTO =
                    new MemberSecurityDTO(
                            member.getMid(),
                            member.getMpw(),
                            member.getEmail(),
                            member.isDel(),
                            member.isSocial(),
                            member.getRoleSet()
                                    .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                                    .collect(Collectors.toList())
                    );

            return memberSecurityDTO;
        }
    }
      
    // p751-752
    private String getKakaoEmail(Map<String, Object> paramMap){

        log.info("KAKAO-----------------------------------------");

        Object value = paramMap.get("kakao_account");
        
        log.info(value);

        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String)accountMap.get("email");
        log.info("email..." + email);

        return email;
    }     
    
}
