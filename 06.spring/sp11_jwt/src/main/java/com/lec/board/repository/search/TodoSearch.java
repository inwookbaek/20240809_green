package com.lec.board.repository.search;

import org.springframework.data.domain.Page;

import com.lec.board.dto.PageRequestDTO;
import com.lec.board.dto.TodoDTO;

public interface TodoSearch {
    Page<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
