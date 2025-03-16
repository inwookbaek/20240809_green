package com.lec.board.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lec.board.entity.UserEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 📌 Spring Security에서 사용자 정보를 담는 CustomUserDetails 클래스
 * - UserDetails 인터페이스를 구현하여 사용자 정보를 Security 인증 시스템과 연결
 * - 이 클래스는 Spring Security의 UserDetails 인터페이스를 구현하여 사용자의 인증 및 권한 정보를 저장하는 역할
 * 🔹 CustomUserDetails 클래스의 역할
      1️⃣ UserEntity를 기반으로 Spring Security에서 사용할 사용자 정보를 제공
	  2️⃣ 사용자의 아이디(username), 비밀번호(password) 를 반환
	  3️⃣ 사용자의 권한(Role) 을 Spring Security가 이해할 수 있도록 변환
	  4️⃣ 계정이 만료되지 않았는지, 비밀번호가 만료되지 않았는지, 계정이 잠기지 않았는지 등을 설정
	  5️⃣ 인증 과정에서 UserDetailsService가 이 객체를 반환하여 Spring Security가 사용자 정보를 인식할 수 있도록 함
 */
public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;  // 데이터베이스에서 가져온 사용자 정보

    /**
     * 📌 생성자
     * - 데이터베이스에서 조회한 UserEntity 객체를 받아서 설정
     */
    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * 📌 사용자의 권한(Role) 반환
     * - 사용자의 역할(예: ROLE_USER, ROLE_ADMIN 등)을 Security에서 사용할 수 있도록 변환
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userEntity.getRole())); // 권한을 리스트로 반환
    }


    /**
     * 📌 사용자의 비밀번호 반환 (인증 시 사용됨)
     */
    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    /**
     * 📌 사용자의 아이디(username) 반환 (인증 시 사용됨)
     */
    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    /**
     * 📌 계정이 만료되지 않았는지 여부 (true: 만료되지 않음)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;  // 만료 기능을 사용하지 않으므로 항상 true 반환
    }

    /**
     * 📌 계정이 잠겨있지 않은지 여부 (true: 잠기지 않음)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;  // 계정 잠금 기능을 사용하지 않으므로 항상 true 반환
    }

    /**
     * 📌 비밀번호가 만료되지 않았는지 여부 (true: 만료되지 않음)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 비밀번호 만료 기능을 사용하지 않으므로 항상 true 반환
    }

    /**
     * 📌 계정이 활성화 상태인지 여부 (true: 활성화됨)
     */
    @Override
    public boolean isEnabled() {
        return true;  // 계정 비활성화 기능을 사용하지 않으므로 항상 true 반환
    }
}
