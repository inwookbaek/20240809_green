package com.lec.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.mybatis.dao.BoardMapper;
import com.lec.mybatis.dto.Board;

@Service
public class BoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<Board> getAllBoardList() {
        return boardMapper.getAllBoardList();
    }
    
}
