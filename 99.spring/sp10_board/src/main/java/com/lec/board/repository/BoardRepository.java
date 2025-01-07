package com.lec.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lec.board.domain.Board;
import com.lec.board.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

	@Query(value="select now()", nativeQuery = true)
	String getTime();
	
}
