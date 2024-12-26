package com.lec.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lec.board.domain.Member;

import jakarta.transaction.Transactional;

public interface MemberRepository extends JpaRepository<Member, String>{

	// 카카오등 social서비스를 통해서 회원가입된 회원들은 제외하고 일반회원들만
	// 가져오도록 social속성이 false인 사용자만 대상으로 한다.
	@EntityGraph(attributePaths = "roleSet")
	@Query("select m from Member m where m.mid = :mid and m.social = false")
	Optional<Member> getWithRoles(@Param("mid") String mid);
	
	/*
		소셜로그인에 상ㅇ한 이메일이 존재하는 경우와 그렇지 않은 경우에 처리방법이 중요하다.
		soicial의 email과 같은 이메일을 가진 회원이 있을 경우 로그인 자체가 완료되어야 한다.
		Member.social속성을 이용해서 만약 악의적인 사용자가 현재 사용자의 이메일을 안다고 해도
		직접 로그인할 때는 social설정이 false인 경우만 조회되기 때문에 로그인이 되지 않는다.
		대신에 소셜서비스를 통해서 로그인한 사용자의 경우 일반 로그인을 하기 위해서는 일반회원
		으로 전환할 수 있는 기능(화면)이 제공되어야 한다.
	*/
	@EntityGraph(attributePaths = "roleSet")
	Optional<Member> findByEmail(@Param("email")String email);
	
	/*
		소셜로그인으로 로그인하면 무조건 패스워드가 12345를 인코딩한 값으로 저장되기 때문에
		패스워드가 변경된 상황에서 테스트가 불가능하다. 이를 위해 패스워드를 수정할 수 있는
		기능을 추가 해야 한다. @Query은 주로 select시 이용하지만 update를 하기 위해서 @Modifying
		어노테이션을 정의하면 update/delete/insert도 처리가능하다.
	*/
	@Modifying
	@Transactional
	@Query("update Member m set m.mpw = :mpw where mid = :mid")
	void updatePassword(@Param("mpw") String password, @Param("mid") String mid);
}
