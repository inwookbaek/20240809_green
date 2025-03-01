package com.lec.axios.service;

import com.lec.axios.dto.PageRequestDTO;
import com.lec.axios.dto.PageResponseDTO;
import com.lec.axios.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}