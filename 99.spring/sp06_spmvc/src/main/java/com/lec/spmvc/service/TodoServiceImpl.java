package com.lec.spmvc.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lec.spmvc.domain.TodoVO;
import com.lec.spmvc.dto.TodoDTO;
import com.lec.spmvc.mapper.TodoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    
	private TodoMapper todoMapper;
    private ModelMapper modelMapper;
    
    @Override
    public void register(TodoDTO todoDTO) {

        log.info(modelMapper);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);

    }

	@Override
	public String getTime() {
		 log.info("============> " + todoMapper.getTime());
		 return todoMapper.getTime();
	}   
}
