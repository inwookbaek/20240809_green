package com.lec.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lec.board.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	//@Query(value="select * from reply r where r.bno = :bno", nativeQuery = true)
	@Query("select r from Reply r where r.board.bno = :bno")
	Page<Reply> listOfBoard(@Param("bno") Long bno, Pageable pageable);
}
