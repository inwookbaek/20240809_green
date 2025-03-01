package com.lec.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lec.board.domain.Board;
import com.lec.board.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

	@EntityGraph(attributePaths = {"imageSet"})
	@Query("select b from Board b where b.bno = :bno")
	Optional<Board> findByIdWithImages(@Param("bno") Long bno);
}
