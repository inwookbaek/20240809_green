package com.lec.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.lec.board.domain.Board;
import com.lec.board.domain.Reply;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyRepositoryTests {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Test
	public void testReplyInsert() {
		
		Long bno = 100L;
		Board board = Board.builder().bno(bno).build();
		
		Reply reply = Reply.builder()
				// .bno(bno)
				.board(board)
				.replyText("댓글등록하기.....")
				.replyer("replyer1")
				.build();
		
		replyRepository.save(reply);
	}
	
	
	@Transactional
	@Test
	public void testBoardReplies() {
		
		Long bno = 100L;
		Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
		Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
		result.getContent().forEach(reply -> {
			log.info(reply);
		});
	}
}
