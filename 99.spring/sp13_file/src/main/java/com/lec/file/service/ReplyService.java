package com.lec.file.service;

import com.lec.file.dto.PageRequestDTO;
import com.lec.file.dto.PageResponseDTO;
import com.lec.file.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}