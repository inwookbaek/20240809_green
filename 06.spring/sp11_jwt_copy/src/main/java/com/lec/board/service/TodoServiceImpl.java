package com.lec.board.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lec.board.domain.Todo;
import com.lec.board.dto.TodoDTO;
import com.lec.board.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
	
	private final TodoRepository todoRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public Long register(TodoDTO todoDTO) {
		
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		Long tno = todoRepository.save(todo).getTno();
		return tno;
	}

}
