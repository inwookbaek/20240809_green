package com.lec.kakao.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.kakao.domain.Board;
import com.lec.kakao.dto.BoardDTO;
import com.lec.kakao.dto.BoardListAllDTO;
import com.lec.kakao.dto.BoardListReplyCountDTO;
import com.lec.kakao.dto.PageRequestDTO;
import com.lec.kakao.dto.PageResponseDTO;
import com.lec.kakao.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional  // 스프링은 해당 객체를 감싸는 별도의 클래스를 생성하는 애너테이션
public class BoardServiceImpl implements BoardService {

	private final ModelMapper modelMapper;
	
	private final BoardRepository boardRepository;
	
//	@Override
//	public Long register(BoardDTO boardDTO) {
//		Board board = modelMapper.map(boardDTO, Board.class);
//		Long bno = boardRepository.save(board).getBno();
//		return bno;
//	}
	
	// p642 등록처리와 테스트
	@Override
	public Long register(BoardDTO boardDTO) {
        Board board = dtoToEntity(boardDTO);
        Long bno = boardRepository.save(board).getBno();
		return bno;
	}
	
//    @Override
//    public BoardDTO readOne(Long bno) {
//        Optional<Board> result = boardRepository.findById(bno);
//        Board board = result.orElseThrow();
//        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
//        return boardDTO;
//    }
    
	// p644
    @Override
    public BoardDTO readOne(Long bno) {
        //board_image까지 조인 처리되는 findByWithImages()를 이용
        Optional<Board> result = boardRepository.findByIdWithImages(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = entityToDTO(board);
        return boardDTO;
    }  
    
//    @Override
//    public void modify(BoardDTO boardDTO) {
//        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
//        Board board = result.orElseThrow();
//        board.change(boardDTO.getTitle(), boardDTO.getContent());
//        boardRepository.save(board);
//    }
    
    // p645 - 게시물 수정처리
    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());

        //첨부파일의 처리
        board.clearImages();
        if(boardDTO.getFileNames() != null){
            for (String fileName : boardDTO.getFileNames()) {
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
    
//    @Override
//    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
//
//        String[] types = pageRequestDTO.getTypes();
//        String keyword = pageRequestDTO.getKeyword();
//        Pageable pageable = pageRequestDTO.getPageable("bno");
//
//        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
//
//        return null;
//    }    
    
    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

//        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
        Page<Board> result = boardRepository.searchAllPageImpl(types, keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board,BoardDTO.class)).collect(Collectors.toList());

        // PageResponseDTO에서 @Builder(builderMethodName = "withAll") 빌더메서드이름을 정의
        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();

    }

    @Override
    public PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {

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

    // p634 댓글갯수와 DTO처리
//	@Override
//	public PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
//		return null;	
//	}   
	
	// p648 게시물 목록처리
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
