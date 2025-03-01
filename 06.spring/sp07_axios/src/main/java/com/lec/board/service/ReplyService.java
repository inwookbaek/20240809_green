package com.lec.board.service;

import com.lec.board.dto.PageRequestDTO;
import com.lec.board.dto.PageResponseDTO;
import com.lec.board.dto.ReplyDTO;

public interface ReplyService {

	Long register(ReplyDTO replyDTO);
	Long saveReply(ReplyDTO replyDTO);
	ReplyDTO read(Long rno);
	void modify(ReplyDTO replyDTO);
	void remove(Long rno);
	PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
