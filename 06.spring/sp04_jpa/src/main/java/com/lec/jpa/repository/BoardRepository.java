package com.lec.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lec.jpa.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	@Query(value = "select now()", nativeQuery = false)
	String getTime();
}
