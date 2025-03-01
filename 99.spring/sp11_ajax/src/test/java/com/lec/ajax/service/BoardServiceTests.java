package com.lec.ajax.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.ajax.domain.Board;
import com.lec.ajax.dto.BoardDTO;
import com.lec.ajax.dto.PageRequestDTO;
import com.lec.ajax.dto.PageResponseDTO;
import com.lec.ajax.repository.BoardRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;
    
    @Autowired
    private BoardRepository boardRepository; 

    @Test
    @DisplayName("Wrapper Class 정보 출력")
    public void testRegister() {
        log.info(boardService.getClass().getName());
    }
    
    @Test
    @DisplayName("insert data- BoardService")
    public void testInsert() {
    	log.info(boardService.getClass().getName());
    	
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);    	
    }
 
	@Test
	@DisplayName("insert data- testInsertBoardRepository")
	public void testInsertBoardRepository() {
		// 왜 이 프로젝트는 테이블의 날짜(regdate, moddate)가 안들어 가는지 확인할 것?
		Board board = Board.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();
		Board result = boardRepository.save(board);	
		
		log.info("bno: " + result.getBno()); 
	} 
	 
	@Test
	@DisplayName("update...")
    public void testModify() {

        //변경에 필요한 데이터만
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(111L)
                .title("Updated....111")
                .content("Updated content 111...")
                .build();

        boardService.modify(boardDTO);

    }	
	
    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("user00")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);

    }	
}
