package com.lec.file.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.file.domain.Board;
import com.lec.file.dto.BoardListAllDTO;
import com.lec.file.dto.BoardListReplyCountDTO;

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
