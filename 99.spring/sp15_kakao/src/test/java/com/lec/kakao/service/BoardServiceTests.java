package com.lec.kakao.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.kakao.domain.Board;
import com.lec.kakao.dto.BoardDTO;
import com.lec.kakao.dto.BoardImageDTO;
import com.lec.kakao.dto.BoardListAllDTO;
import com.lec.kakao.dto.PageRequestDTO;
import com.lec.kakao.dto.PageResponseDTO;
import com.lec.kakao.repository.BoardRepository;

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
	 
//	@Test
//	@DisplayName("update...")
//    public void testModify() {
//        //변경에 필요한 데이터만
//        BoardDTO boardDTO = BoardDTO.builder()
//                .bno(111L)
//                .title("Updated....111")
//                .content("Updated content 111...")
//                .build();
//
//        boardService.modify(boardDTO);
//    }	
	
	// p646 - BoardImage들을 삭제하고 새로운 첨부파일 하나만 가지도록 변경
	// 테스트실행후 testReadAll()실행으로 확인
	@Test
    public void testModify() {
        //변경에 필요한 데이터
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated....101")
                .content("Updated content 101...")
                .build();
        //첨부파일을 하나 추가
        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID()+"_zzz.jpg"));
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
    
    // p644 - 게시물과 첨부파일정보가 한 번에 처리
    @Test
    public void testReadAll() {
        Long bno = 101L;
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);
        for (String fileName : boardDTO.getFileNames()) {
            log.info(fileName);
        }//end for
    } 
    
    // p647 - 게시물 삭제처리  
    @Test
    public void testRemoveAll() {
        Long bno = 1L;
        boardService.remove(bno);
    }  
    
    // p648 - 첨부파일이 존재하는 경우 모든 첨부파일의 순서대로 출력하는 것 확인
    @Test
    public void testListWithAll() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardListAllDTO> responseDTO =
                boardService.listWithAll(pageRequestDTO);

        List<BoardListAllDTO> dtoList = responseDTO.getDtoList();

        dtoList.forEach(boardListAllDTO -> {
            log.info(boardListAllDTO.getBno()+":"+boardListAllDTO.getTitle());

            if(boardListAllDTO.getBoardImages() != null) {
                for (BoardImageDTO boardImage : boardListAllDTO.getBoardImages()) {
                    log.info(boardImage);
                }
            }

            log.info("-------------------------------");
        });
    }    
}
