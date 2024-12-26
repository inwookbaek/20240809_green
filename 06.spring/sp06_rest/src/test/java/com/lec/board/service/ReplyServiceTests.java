package com.lec.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.lec.board.domain.Reply;
import com.lec.board.dto.ReplyDTO;
import com.lec.board.repository.ReplyRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyServiceTests {

	@Autowired
	private ReplyService replyService;
	
	@Test
	public void testReplyRegister() {
		
		ReplyDTO replyDTO = ReplyDTO.builder()
				.replyText("댓글등록하기 - service test....")
				.replyer("replyer2")
				.bno(100L)
				.build();
		log.info(replyService.register(replyDTO));
	}

}
