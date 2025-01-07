package com.lec.kakao.security.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* 
	회원서비스와 DTO
	
	스프링시큐리티에서는 UserDetails라는 타입을 이용하기 때문에 일반적인 DTO와는 조금 다르게 처리해야 한다.
	MemberSecurityDTO는 org.springframework.security.core.userdetails.User라는 클래스를 부모클래스로 사용한다.
	User클래스는 UserDetails인터페이스를 구현한 클래스로 최대한 간단하게 UserDetails타입을 생성할 수 있는 방법을 제공한.
*/
@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User 
	 /* p753 - 소셜로그인에서도 이 객체를 사용하기 위해 OAuth2Uer 구현 */ implements OAuth2User {

    private String mid;
    private String mpw;
    private String email;
    private boolean del;
    private boolean social;
    
    // p754
    private Map<String, Object> props; // 소셜로그인정보

    public MemberSecurityDTO(String username, String password, String email, boolean del, boolean social,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.mid = username;
        this.mpw = password;
        this.email = email;
        this.del = del;
        this.social = social;
    }
    
    // p754    
    public Map<String, Object> getAttributes() {
        return this.getProps();
    }

    // p754    
    @Override
    public String getName() {
        return this.mid;
    }    
}

