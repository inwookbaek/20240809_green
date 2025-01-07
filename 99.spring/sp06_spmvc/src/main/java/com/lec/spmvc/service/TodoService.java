package com.lec.spmvc.service;

import com.lec.spmvc.dto.TodoDTO;

public interface TodoService {
	
	String getTime();
    void register(TodoDTO todoDTO);
    //List<TodoDTO> getAll();
//    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
//    TodoDTO getOne(Long tno);
//    void remove(Long tno);
//    void modify(TodoDTO todoDTO);
}
