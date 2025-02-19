package com.lec.board.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.board.domain.Board;
import com.lec.board.dto.BoardListReplyCountDTO;

public interface BoardSearch {

	Page<Board> searchLike(Pageable pageable);
	Page<Board> searchPageable(Pageable pageable);
	Page<Board> searchBooleanBuilder(Pageable pageable);
	Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
	Page<Board> searchAllImpl(String[] types, String keyword, Pageable pageable);
	
	Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
