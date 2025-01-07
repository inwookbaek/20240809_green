package com.lec.jpa.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.jpa.domain.Board;

public interface BoardSearch {
    
	Page<Board> search1(Pageable pageable);
	Page<Board> searchLike(Pageable pageable);
	Page<Board> searchPagable(Pageable pageable);
	Page<Board> searchBooleanBuilder(Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
    Page<Board> searchAllPageImpl(String[] types, String keyword, Pageable pageable);
}
