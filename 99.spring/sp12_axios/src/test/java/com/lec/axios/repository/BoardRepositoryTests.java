package com.lec.axios.repository;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.lec.axios.domain.Board;
import com.lec.axios.dto.BoardListReplyCountDTO;
import com.lec.axios.service.BoardService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	BoardService boardService;
	
	@Test
	@DisplayName("게시판 샘플 데이타 생성")
	public void testInsert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Board board = Board.builder()
					.title("title..." + i)
					.content("content..." + i)
					.writer("writer..." + (i%10))
					.build();
			Board result = boardRepository.save(board);
			log.info("BNO: " + result.getBno());
		});
		
	}
	
	@Test
	@DisplayName("게시판 샘플 데이타 한 건 생성")
	public void testInsertOne() {
		Board board = Board.builder()
				.title("title999")
				.content("content999")
				.writer("writer999")
				.build();
		Board result = boardRepository.save(board);
		log.info("BNO: " + result.getBno());
		
	}	
	
	@Test
	@DisplayName("게시판 한 건 조회")
	public void testSelect() {
		
		Long bno = 100L;
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		log.info(board);		
	}

	@Test
	@DisplayName("게시판 수정")
	public void testUpdate() {
		
		Long bno = 100L;
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		board.change("제목100", "글 상세내용 100");
		boardRepository.save(board);
		log.info(board);		
	}
	
	
	@Test
	@DisplayName("게시판 삭제")
	public void testDelete() {
		
		Long bno = 3L;
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		boardRepository.deleteById(bno);
		log.info(board);		
	}
	
	@Test
	@DisplayName("페이징 처리")
	public void testPaging() {
		
		Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
		Page<Board> result = boardRepository.findAll(pageable);
		log.info("총건수: " + result.getTotalElements());
		log.info("총페이지: " + result.getTotalPages());
		log.info("현페이지: " + result.getNumber());
		log.info("페이지크기: " + result.getSize());
		
		List<Board> boardList = result.getContent();
		boardList.forEach(board -> log.info(board));
	}
	
    @Test
    @DisplayName("검색 like")
    public void testSearchLike() {
        // 2 page order by bno desc
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
        boardRepository.searchLike(pageable);

    }	
    
    @Test
    @DisplayName("검색 limit")
    public void testSearchPagable() {
    	// 2 page order by bno desc
    	Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
    	boardRepository.searchPagable(pageable);
    	
    }	
    
    @Test
    @DisplayName("검색 where and or")
    public void testSearchBooleanBuilder() {
    	// 2 page order by bno desc
    	Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
    	boardRepository.searchBooleanBuilder(pageable);
    	
    }	
    
    @Test
    @DisplayName("검색 all")
    public void testSearchAll() {

    	String[] types = {"t","c","w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable );
    	
    }	
    
    @Test
    @DisplayName("검색 PageImpl")
    public void testSearchAllPageImpl() {
    	
    	String[] types = {"t","c","w"};
    	String keyword = "1";
    	Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
    	Page<Board> result = boardRepository.searchAllPageImpl(types, keyword, pageable );
 
        // total pages
        log.info(result.getTotalPages());

        // pag size
        log.info(result.getSize());

        // pageNumber
        log.info(result.getNumber());

        // prev next
        log.info(result.hasPrevious() +": " + result.hasNext());

        result.getContent().forEach(board -> log.info(board));
    }	
    
    @Test
    public void testSearchReplyCount() {

        String[] types = {"t","c","w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable );

        //total pages
        log.info(result.getTotalPages());
        //pag size
        log.info(result.getSize());
        //pageNumber
        log.info(result.getNumber());
        //prev next
        log.info(result.hasPrevious() +": " + result.hasNext());

        result.getContent().forEach(board -> log.info(board));
    }    
}