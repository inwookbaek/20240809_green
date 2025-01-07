package com.lec.board.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.lec.board.domain.Board;
import com.lec.board.dto.BoardListReplyCountDTO;
import com.lec.board.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardRepositoryTests {

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	BoardService boardService;

	@Test
	@DisplayName("검색(like)")
	public void testSearchLike() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		boardRepository.searchLike(pageable);
	}
	
	@Test
	@DisplayName("페이징처리")
	public void testPaging() {
		Pageable pageable =  PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Board> result = boardRepository.findAll(pageable);
		log.info("총건수 : " + result.getTotalElements());
		log.info("총페이지 : " + result.getTotalPages());
		log.info("현페이지 : " + result.getNumber());
		log.info("페이지크기 : " + result.getSize());
		
		List<Board> boardList = result.getContent();
		boardList.forEach(board -> log.info(board));

	}
	
	@Test
	@DisplayName("검색(limit)")
	public void testSearchPagable() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		boardRepository.searchPageable(pageable);
	}	

	@Test
	@DisplayName("or/and 조건검색")
	public void searchBooleanBuilder() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		boardRepository.searchBooleanBuilder(pageable);
		
	}
	
	@Test
	@DisplayName("검색조건 - all")
	public void searchAll() {
		
		String[] types = {"t", "c", "w"};
		String keyword = "1";
		
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		boardRepository.searchAll(types, keyword, pageable);
		
	}
	
	@Test
	@DisplayName("검색조건 - PageImpl")
	public void searchAllImpl() {
		
		String[] types = {"t", "c", "w"};
		String keyword = "1";
		
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		Page<Board> result = boardRepository.searchAllImpl(types, keyword, pageable);
		
		log.info("총페이지   = " + result.getTotalPages());
		log.info("페이지크기 = " + result.getSize());
		log.info("현재페이지 = " + result.getNumber());
		log.info("이전페이지 = " + result.hasPrevious());
		log.info("다음페이지 = " + result.hasNext());
		
		result.getContent().forEach(board -> log.info(board));
		
	}
	
	@Test
	public void testSearchReplyCount() {
		String[] types = {"t","c","w"};
		String keyword = "1";
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);
		
		// total pages
		log.info(result.getTotalPages());
		
		// page size
		log.info(result.getSize());
		
		// page number
		log.info(result.getNumber());
		
		// prev / next number
		log.info(result.hasPrevious() + ", " + result.hasNext());
		
		// content
		result.getContent().forEach(board -> log.info(board));
	}
	
}