package com.lec.axios.service;

import com.lec.axios.dto.BoardDTO;
import com.lec.axios.dto.BoardListReplyCountDTO;
import com.lec.axios.dto.PageRequestDTO;
import com.lec.axios.dto.PageResponseDTO;

public interface BoardService {
	
	Long register(BoardDTO boardDTO);
	BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
    
    //댓글의 숫자까지 처리
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
