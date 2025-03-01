package com.lec.ajax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lec.ajax.domain.Board;
import com.lec.ajax.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

	@Query(value="select now()", nativeQuery = true)
	String getTime();
	
}
