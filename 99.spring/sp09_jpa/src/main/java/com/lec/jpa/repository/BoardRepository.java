package com.lec.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lec.jpa.domain.Board;
import com.lec.jpa.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

	@Query(value="select now()", nativeQuery = true)
	String getTime();
	
}
