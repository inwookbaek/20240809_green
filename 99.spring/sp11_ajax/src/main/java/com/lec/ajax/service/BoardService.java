package com.lec.ajax.service;

import com.lec.ajax.dto.BoardDTO;
import com.lec.ajax.dto.BoardListReplyCountDTO;
import com.lec.ajax.dto.PageRequestDTO;
import com.lec.ajax.dto.PageResponseDTO;

public interface BoardService {
	
	Long register(BoardDTO boardDTO);
	BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
    
    //댓글의 숫자까지 처리
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
