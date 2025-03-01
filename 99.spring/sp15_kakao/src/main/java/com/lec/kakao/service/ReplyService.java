package com.lec.kakao.service;

import com.lec.kakao.dto.PageRequestDTO;
import com.lec.kakao.dto.PageResponseDTO;
import com.lec.kakao.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}