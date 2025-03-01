package com.lec.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.board.domain.Board;
import com.lec.board.dto.BoardDTO;
import com.lec.board.dto.BoardListAllDTO;
import com.lec.board.dto.BoardListReplyCountDTO;
import com.lec.board.dto.PageRequestDTO;
import com.lec.board.dto.PageResponseDTO;
import com.lec.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

	private final ModelMapper modelMapper;
	private final BoardRepository boardRepository;

//	@Override // 댓글숫자카운트처리전
//	public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
//
//		String[] types = pageRequestDTO.getTypes();
//		String keyword = pageRequestDTO.getKeyword();
//		Pageable pageable = pageRequestDTO.getPageable("bno");
//		
//		Page<Board> result = boardRepository.searchAllImpl(types, keyword, pageable);
//		List<BoardDTO> dtoList = result.getContent()
//									   .stream()
//									   .map(board -> modelMapper.map(board, BoardDTO.class))
//									   .collect(Collectors.toList());
//		
//		
//		return PageResponseDTO.<BoardDTO>withAll()
//				.pageRequestDTO(pageRequestDTO)
//				.dtoList(dtoList)
//				.total((int)result.getTotalElements())
//				.build();
//	}
	
	@Override // 댓글숫자카운트처리
	public PageResponseDTO<BoardListReplyCountDTO> list(PageRequestDTO pageRequestDTO) {

		String[] types = pageRequestDTO.getTypes();
		String keyword = pageRequestDTO.getKeyword();
		Pageable pageable = pageRequestDTO.getPageable("bno");
		
		Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);
		
		return PageResponseDTO.<BoardListReplyCountDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(result.getContent())
				.total((int)result.getTotalElements())
				.build();
	}
	
	@Override
	public Long register(BoardDTO boardDTO) {
		// Board board = modelMapper.map(boardDTO, Board.class);
		Board board = dtoToEntity(boardDTO);
		Long bno = boardRepository.save(board).getBno();
		return bno;
	}

	@Override
	public BoardDTO readOne(Long bno) {
		
		// Optional<Board> result = boardRepository.findById(bno);
		Optional<Board> result = boardRepository.findByIdWithImages(bno);
		Board board = result.orElseThrow();
		// BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
		BoardDTO boardDTO = entityToDTO(board);
		return boardDTO;
	}

	@Override
	public void modify(BoardDTO boardDTO) {
		
		Optional<Board> result = boardRepository.findById(boardDTO.getBno());
		Board board = result.orElseThrow();
		board.change(boardDTO.getTitle(), boardDTO.getContent());
		
		// 첨부파일처리
		board.clearImage();
		
		if(boardDTO.getFileNames() != null) {
			for(String fileName:boardDTO.getFileNames()) {
				String[] arr = fileName.split("_");
				board.addImage(arr[0], arr[1]);
			}
		}
		
		boardRepository.save(board);	
	}

	@Override
	public void remove(Long bno) {
		boardRepository.deleteById(bno);
	}

	@Override
	public PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
		
		String[] types = pageRequestDTO.getTypes();
		String keyword = pageRequestDTO.getKeyword();
		Pageable pageable = pageRequestDTO.getPageable("bno");
		
		Page<BoardListAllDTO> result = boardRepository.searchWithAll(types, keyword, pageable);
		
		return PageResponseDTO.<BoardListAllDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(result.getContent())
				.total((int)result.getTotalElements())
				.build();
	}
}
