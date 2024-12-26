package com.lec.board.service;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.board.domain.Board;
import com.lec.board.dto.BoardDTO;
import com.lec.board.repository.BoardRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardServiceTests {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardService boardService;
	
	@Test
	@DisplayName("게시글 한건 등록하기....")
	public void testInsert() {
		log.info("...... " + boardService.getClass().getName());
		
		BoardDTO boardDTO = BoardDTO.builder()
								.title("Test Title........")
								.content("Test Content ......")
								.writer("user00")
								.build();
		Long bno = boardService.register(boardDTO);
		
		log.info("게시글번호 = " + bno);
	}
	
	@Test
	@DisplayName("게시글 여러건 등록하기....")
	public void testInsertMany() {

		IntStream.rangeClosed(1, 100).forEach(i -> {
			
			Board board = Board.builder()
					.title(String.format("%3d Title........", i))
					.content(String.format("%3d Content........", i))
					.writer("user" + (i%10))
					.build();	
			
			Board result = boardRepository.save(board);
			
			log.info("게시글번호 = " + result.getBno());
			
		});
	}
	
	@Test
	@DisplayName("게시글 한건 조회하기....")
	public void testSelect() {	
		
		Long bno = 100L;
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		log.info(board);
		
	}
	
	@Test
	@DisplayName("게시글 수정 하기....")
	public void testUpdate() {	
		
		Long bno = 100L;
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		board.change("[수정] -> " + board.getTitle(), "[수정] -> " + board.getContent());
		boardRepository.save(board);
		log.info(board);
		
	}
	
	@Test
	@DisplayName("게시글 삭제 하기....")
	public void testDelete() {	
		
		Long bno = 1L;
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		boardRepository.deleteById(bno);
		log.info(board);
		
	}

}











