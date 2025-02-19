package com.lec.board.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
	이 클래스는 Spring Security의 UserDetails 인터페이스를 구현한 엔티티입니다.
	즉, 회원 정보와 함께 보안 관련 정보(권한 등)를 포함하는 클래스입니다.
	데이터베이스에 저장될 회원 정보(username, password, roles 등) 를 관리하며,
 	Spring Security가 이 정보를 활용해 인증 및 권한 부여를 처리합니다.
*/

@Entity // ✅ JPA 엔티티 선언 (DB의 'member' 테이블과 매핑)
@Getter // ✅ 모든 필드에 대한 Getter 자동 생성 (Lombok 사용)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // ✅ 기본 생성자를 `protected`로 설정 (JPA 기본 정책)
@AllArgsConstructor // ✅ 모든 필드를 포함하는 생성자 자동 생성
@Builder // ✅ 객체를 유연하게 생성할 수 있도록 Builder 패턴 적용
@EqualsAndHashCode(of = "id") // ✅ `id` 필드만을 기준으로 equals() 및 hashCode() 메서드 자동 생성
public class Member implements UserDetails {

    @Id
    @GeneratedValue // ✅ 자동 증가(Auto Increment) 설정
    @Column(name = "member_id", updatable = false, unique = true, nullable = false) // ✅ 필드 매핑 설정
    private Long id; // 🔹 회원 고유 ID (PK)

    @Column(nullable = false)
    private String username; // 🔹 로그인 아이디 (Spring Security에서 필수)

    @Column(nullable = false)
    private String password; // 🔹 비밀번호 (Spring Security에서 필수)

    private String nickname; // 🔹 사용자 닉네임

    private String address; // 🔹 도로명 주소

    private String phone; // 🔹 전화번호

    private String profileImg; // 🔹 프로필 이미지 URL

    /**
     * 🔹 회원이 가진 권한 목록 (e.g., ROLE_USER, ROLE_ADMIN)
     * - `@ElementCollection`을 사용하여 별도의 테이블 없이 리스트 컬럼으로 저장
     * - `FetchType.EAGER`: 회원 정보를 조회할 때 권한 정보도 함께 불러옴
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    /**
     * getAuthorites()
       멤버가 가지고 있는 권한(authority) 목록을 SimpleGrantedAuthority로 변환하여 반환
       나머지 Override 메서드들 전부 true로 반환하도록 설정
     * 🔹 UserDetails 인터페이스의 필수 구현 메서드
     * - Spring Security에서 사용자의 권한 정보를 가져오는 역할
     * - SimpleGrantedAuthority 객체로 변환하여 반환해야 함
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new) // ✅ 권한 문자열을 `SimpleGrantedAuthority` 객체로 변환
                .collect(Collectors.toList());
    }

    /**
     * 🔹 계정이 만료되지 않았는지 여부 (true = 만료되지 않음)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // 기본적으로 만료되지 않도록 설정
    }

    /**
     * 🔹 계정이 잠기지 않았는지 여부 (true = 잠기지 않음)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // 기본적으로 계정이 잠기지 않도록 설정
    }

    /**
     * 🔹 비밀번호가 만료되지 않았는지 여부 (true = 만료되지 않음)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 기본적으로 비밀번호가 만료되지 않도록 설정
    }

    /**
     * 🔹 계정이 활성화되어 있는지 여부 (true = 활성화됨)
     */
    @Override
    public boolean isEnabled() {
        return true; // 기본적으로 활성화된 계정으로 설정
    }
}
