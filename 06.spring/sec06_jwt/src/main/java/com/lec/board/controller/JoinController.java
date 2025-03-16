package com.lec.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lec.board.dto.JoinDTO;
import com.lec.board.service.JoinService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@ResponseBody
public class JoinController {
    
    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {

        // log.info("username = {}", joinDTO.getUsername());
        
        joinService.joinProcess(joinDTO);

        return "ok";
    }
}