package com.lec.board.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.board.domain.Board;

public interface BoardSearch {
    
	Page<Board> searchLike(Pageable pageable);
	Page<Board> searchPagable(Pageable pageable);
	Page<Board> searchBooleanBuilder(Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
    Page<Board> searchAllPageImpl(String[] types, String keyword, Pageable pageable);
}
