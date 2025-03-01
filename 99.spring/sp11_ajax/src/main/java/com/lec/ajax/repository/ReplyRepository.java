package com.lec.ajax.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lec.ajax.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.board.bno = :bno")
    // @Query(value="select * from Reply r where r.board_bno = :bno", nativeQuery = true)
     Page<Reply> listOfBoard(@Param("bno") Long bno, Pageable pageable);
}
