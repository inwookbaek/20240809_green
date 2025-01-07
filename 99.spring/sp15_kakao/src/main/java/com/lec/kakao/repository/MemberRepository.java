package com.lec.kakao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lec.kakao.domain.Member;

import jakarta.transaction.Transactional;

public interface MemberRepository extends JpaRepository<Member, String> {

	// p621
	// ㅎ위 엔티티를 로딩하는 가장 간단한 방법은 즉시(eager)로딩을 적용하는 것이지만
	// 가능하면 지연로딩(lazy)를 이용하는 것이 기본적인 방식이므로 @EntityGraph를 적용
	// N+1 문제 해결: 기본적으로 JPA는 연관된 엔티티를 조회할 때 지연 로딩(lazy loading)을 사용
	// 이는 여러 개의 추가 쿼리를 발생시킬 수 있다. @EntityGraph를 사용하면 한 번의 쿼리로 
	// 관련된 엔티티들을 함께 로딩할 수 있다.
	// name: 엔티티 그래프의 이름을 지정
    // attributePaths: 함께 로딩할 속성 경로를 지정
	// type: 페치 타입을 지정(기본값은 FETCH)
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.social = false")
    Optional<Member> getWithRoles(@Param("mid") String mid);

    // 752~753 - 소셜로그인후 처리
    // 소셜로그인에 사용한 이메일이 존재하는 경우와 그렇지 않은 경우에 처리방법 결정이 필요
    // social의 email과 같은 이메일을 가진 회원이 있을 경우 로그인 자체가 완료되어야 한다.
    // Member에 social컬럼 속성을 이용, 만약 악의적인 사용자가 현재 사용자의 이메일을 안다고 해도
    // 직접 로그인할 때는 social설정이 false인 경우만 조회되기 때문에 로그인이 되지 않는다.
    // 대신에 소셜서비스를 통해서 로그인한 사용자의 경우 일반 로그인을 하기 위해서는 일반회원으로
    // 전환할 수 있는 화면이 제공되어야 한다.
    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(@Param("email") String email);
    
    // p761-762
    // 소셜로그인으로 로그인하면 무조건 패스워드가 1111을 인코딩한 값으로 저장되므로 패스워드가
    // 변경된 상항에서 테스트가 불가능하다.이를 위해 패스워드를 수정할 수 있는 기능을 추기
    // @Query는 주로 select시 이용하지만 @Modifying과 같이 사용하면 insert/update/delete도 처리가능
    @Modifying
    @Transactional
    @Query("update Member m set m.mpw =:mpw where m.mid = :mid ")
    void updatePassword(@Param("mpw") String password, @Param("mid") String mid);    
}
