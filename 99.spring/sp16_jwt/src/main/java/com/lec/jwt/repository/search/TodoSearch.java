package com.lec.jwt.repository.search;

import org.springframework.data.domain.Page;

import com.lec.jwt.dto.PageRequestDTO;
import com.lec.jwt.dto.TodoDTO;

public interface TodoSearch {
    Page<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
