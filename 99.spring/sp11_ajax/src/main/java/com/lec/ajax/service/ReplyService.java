package com.lec.ajax.service;

import com.lec.ajax.dto.PageRequestDTO;
import com.lec.ajax.dto.PageResponseDTO;
import com.lec.ajax.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}