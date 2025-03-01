package com.lec.mvc.todo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.lec.mvc.todo.dto.TodoDTO;

public enum TodoService {
	INSTANCE;
	
	public void register(TodoDTO todoDTO) {
		// TODO Auto-generated method stub

	}
	
	public List<TodoDTO> getList() {
		
		List<TodoDTO> todoLists = IntStream.range(0, 10)
				.mapToObj(i -> {
					TodoDTO todo = new TodoDTO();
					todo.setTno((long) i);
					todo.setTitle("Todo.... " + i);
					todo.setDueDate(LocalDate.now());
					return todo;
				}).collect(Collectors.toList());
		
		return todoLists;
	}
	
	public TodoDTO get(Long tno) {
		
        TodoDTO dto = new TodoDTO();
        dto.setTno(tno);
        dto.setTitle("Sample Todo");
        dto.setDueDate(LocalDate.now());
        dto.setFinished(true);
        
        return dto;		
	}
}
