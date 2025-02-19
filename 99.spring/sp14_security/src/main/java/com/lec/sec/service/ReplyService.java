package com.lec.sec.service;

import com.lec.sec.dto.PageRequestDTO;
import com.lec.sec.dto.PageResponseDTO;
import com.lec.sec.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}