package com.lec.spmvc.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lec.spmvc.domain.TodoVO;

@Mapper
public interface TodoMapper {

	String getTime();
	void insert(TodoVO todoVO);
}
