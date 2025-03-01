package com.lec.kakao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lec.kakao.domain.Board;
import com.lec.kakao.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

	@Query(value="select now()", nativeQuery = true)
	String getTime();
	
	// p621 @EntityGraph조회 테스트
	// 연관관계가 있는 엔티티를 조회할 경우 지연 로딩으로 설정되어 있으면 연관관계에서 종속된 엔티티는 
	// 쿼리 실행 시 select 되지 않고 proxy 객체를 만들어 엔티티가 적용시킨다. 
	// 하위 엔티티를 로딩하는 가장 간단한 방법은 즉시(eager)로딩을 적용하는 것이지만 가능하면 지연(lazy_
	// 로딩을 이용한 것이 기본적인 방식이다.
	@EntityGraph(attributePaths = {"imageSet"})
    @Query("select b from Board b where b.bno =:bno")
    Optional<Board> findByIdWithImages(@Param("bno") Long bno);	
	
}
