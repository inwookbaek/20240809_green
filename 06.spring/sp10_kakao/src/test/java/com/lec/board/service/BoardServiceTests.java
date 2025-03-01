package com.lec.board.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.board.domain.Board;
import com.lec.board.dto.BoardDTO;
import com.lec.board.dto.BoardImageDTO;
import com.lec.board.dto.BoardListAllDTO;
import com.lec.board.dto.PageRequestDTO;
import com.lec.board.dto.PageResponseDTO;
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

	@Test
	public void testRegisterWithImages() {
		
		log.info(boardService.getClass().getName());
		
		BoardDTO boardDTO = BoardDTO.builder()
				.title("File Attache Test....")
				.content("파일첨부테스트 샘플 내용....")
				.writer("sonny")
				.build();
		
		boardDTO.setFileNames(
				Arrays.asList(
					UUID.randomUUID() + "_aaa.jpg",
					UUID.randomUUID() + "_bbb.jpg",
					UUID.randomUUID() + "_ccc.jpg"
				)
			);
		
		long bno = boardService.register(boardDTO);
		
		log.info("게시글번호 = " + bno);
	}
	
	@Test // 게시글과 첨부파일정보가 한번에 처리
	public void testReadAll() {
		Long bno = 104L;
		BoardDTO boardDTO = boardService.readOne(bno);
		log.info(boardDTO);
		for(String fileName:boardDTO.getFileNames()) {
			log.info(fileName);
		}
	}
	
	@Test
	public void testModify() {
	
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(104L)
				.title("104번게시글 첨부파일 수정하기!!!")
				.content("첨부파일 수정한 상세내용....")
				.build();
		
		// 첨부파일하나 추가
		boardDTO.setFileNames(Arrays.asList(UUID.randomUUID() + "_zzz.jpg"));
		
		boardService.modify(boardDTO);
	}
	
	
	@Test
	public void testRemoveAll() {	
		
		Long bno = 103L;
		boardService.remove(bno);
		
	}
	
	@Test
	public void testListWithAll() {		
		
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.page(1)
				.size(10)
				.build();
		
		PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);
		
		List<BoardListAllDTO> dtoList = responseDTO.getDtoList();
		
		dtoList.forEach(boradListAllDTO -> {
			log.info(boradListAllDTO.getBno() + " : " + boradListAllDTO.getTitle());
			
			if(boradListAllDTO.getBoardImages() != null) {
				for(BoardImageDTO boardImage : boradListAllDTO.getBoardImages()) {
					log.info(boardImage);
				}
			}
			log.info("----------------------");
		});
	}
	
	
	
	
	
}
