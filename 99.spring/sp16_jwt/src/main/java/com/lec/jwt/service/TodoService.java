package com.lec.jwt.service;

import com.lec.jwt.dto.PageRequestDTO;
import com.lec.jwt.dto.PageResponseDTO;
import com.lec.jwt.dto.TodoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoService {

    Long register(TodoDTO todoDTO);
    TodoDTO read(Long tno);
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
    void remove(Long tno);
    void modify(TodoDTO todoDTO);

}