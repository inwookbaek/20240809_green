package com.lec.axios.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.axios.domain.Board;
import com.lec.axios.dto.BoardListReplyCountDTO;

public interface BoardSearch {
    
	Page<Board> searchLike(Pageable pageable);
	Page<Board> searchPagable(Pageable pageable);
	Page<Board> searchBooleanBuilder(Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
    Page<Board> searchAllPageImpl(String[] types, String keyword, Pageable pageable);
    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
