package com.lec.sec.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.sec.domain.Board;
import com.lec.sec.dto.BoardListAllDTO;
import com.lec.sec.dto.BoardListReplyCountDTO;

public interface BoardSearch {
    
	Page<Board> searchLike(Pageable pageable);
	Page<Board> searchPagable(Pageable pageable);
	Page<Board> searchBooleanBuilder(Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
    Page<Board> searchAllPageImpl(String[] types, String keyword, Pageable pageable);
    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
    
    // p629
    // Page<BoardListReplyCountDTO> searchWithAll(String[] types, String keyword, Pageable pageable);   
    
    // p634
    Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);   
}
