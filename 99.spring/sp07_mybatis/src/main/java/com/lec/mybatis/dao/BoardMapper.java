package com.lec.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lec.mybatis.dto.Board;

@Mapper
public interface BoardMapper {
    List<Board> getAllBoardList();
}
