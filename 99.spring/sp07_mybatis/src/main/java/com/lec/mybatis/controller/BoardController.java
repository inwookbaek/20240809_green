package com.lec.mybatis.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.mybatis.dto.Board;
import com.lec.mybatis.service.BoardService;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public List<Board> findAll() {
        return boardService.getAllBoardList();
    }

}