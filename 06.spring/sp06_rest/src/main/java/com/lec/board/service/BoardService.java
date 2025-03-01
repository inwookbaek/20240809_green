package com.lec.board.service;

import com.lec.board.dto.BoardDTO;
import com.lec.board.dto.BoardListReplyCountDTO;
import com.lec.board.dto.PageRequestDTO;
import com.lec.board.dto.PageResponseDTO;


public interface BoardService {

	Long register(BoardDTO boardDTO);
	BoardDTO readOne(Long bno);
	void modify(BoardDTO boardDTO);
	void remove(Long bno);
	// PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
	// 댓글카운트처리
	PageResponseDTO<BoardListReplyCountDTO> list(PageRequestDTO pageRequestDTO);
}
