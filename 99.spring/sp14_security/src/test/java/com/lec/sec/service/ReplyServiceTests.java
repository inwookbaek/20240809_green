package com.lec.sec.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.sec.dto.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testRegister() {

        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("ReplyDTO Text")
                .replyer("replyer")
                .bno(100L)
                .build();

        log.info(replyService.register(replyDTO));
    }
    
    @Test
    public void testRepeat() { 
    	for (int i=0;i<=100;i++) {
    		testRegister();    		
    	}
    }
}