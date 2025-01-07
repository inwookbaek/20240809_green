package com.lec.board.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.lec.board.domain.Board;
import com.lec.board.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

	public BoardSearchImpl() {
		super(Board.class);
	}

	@Override
	public Page<Board> searchLike(Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);    // select * from board;
		query.where(board.title.contains("1"));  // where title like '%1%'
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		log.info("검색건수 = " + count);
		return null;
	}

	@Override
	public Page<Board> searchPageable(Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);    // select * from board;
		query.where(board.title.contains("1"))  // where title like '%1%'
		     .limit(1)
		     .offset(2);
		// limit ?, ?
		this.getQuerydsl().applyPagination(pageable, query);
		List<Board> list = query.fetch();
		long count = query.limit(1).offset(2).fetchCount();
		log.info("검색건수 = " + count);
		
		return null;
	}

	@Override
	public Page<Board> searchBooleanBuilder(Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.or(board.title.contains("11"));
		booleanBuilder.or(board.content.contains("11"));
		
		query.where(booleanBuilder);
		query.where(board.bno.gt(10L)); 
		
		this.getQuerydsl().applyPagination(pageable, query);
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		log.info("검색건수 = " + count);
		
		return null;
	}

	@Override
	public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		if ((types != null || types.length > 0) && keyword != null) { // 검색조건과 검색어가 있다면
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			for(String type:types) {
				switch(type) {
				case "t": 
					booleanBuilder.or(board.title.contains(keyword));
					break;
				case "c": 
					booleanBuilder.or(board.content.contains(keyword));
					break;
				case "w": 
					booleanBuilder.or(board.writer.contains(keyword));
				}
			}
			query.where(booleanBuilder);
		}
		
		this.getQuerydsl().applyPagination(pageable, query);
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		log.info("검색건수 = " + count);
		
		return null;
	}

	@Override
	public Page<Board> searchAllImpl(String[] types, String keyword, Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		if ((types != null || types.length > 0) && keyword != null) { // 검색조건과 검색어가 있다면
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			for(String type:types) {
				switch(type) {
				case "t": 
					booleanBuilder.or(board.title.contains(keyword));
					break;
				case "c": 
					booleanBuilder.or(board.content.contains(keyword));
					break;
				case "w": 
					booleanBuilder.or(board.writer.contains(keyword));
				}
			}
			query.where(booleanBuilder);
		}
		
		this.getQuerydsl().applyPagination(pageable, query);
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		
		return new PageImpl<>(list, pageable, count);
	}

}
