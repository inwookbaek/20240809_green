package com.lec.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.board.service.BoardListService;
import com.lec.board.vo.ActionForward;
import com.lec.board.vo.BoardVO;
import com.lec.board.vo.PageInfo;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		// System.out.println("===> 게시글조회하기");
		// board/board_list.do?p=1&f=writer&q=홍길
		
		int page = 1;
		int limit = 10;
		
		int p = 1;
		String f = "";
		String q = "";
		
		if(req.getParameter("p") != null) p = Integer.parseInt(req.getParameter("p"));
		if(req.getParameter("f") != null) f = req.getParameter("f"); else f = "subject";
		if(req.getParameter("q") != null) q = req.getParameter("q");				

		List<BoardVO> boardList = new ArrayList<>();
		PageInfo pageInfo = new PageInfo();
		
		BoardListService boardListService = BoardListService.getInstance();
		int listCount = boardListService.getListCount(f, q);
		boardList = boardListService.getBoardList(p, limit, f, q);
		
		// 페이징처리
		// 1. 총페이지수 = (총레코드수 / limit) + 1
		int totalPage = (int)((double)listCount / limit + 0.95);

		// 2. 현재페이지(1~10, 11~20 ... 100~101)
		int startPage = (p-1) / 10 * 10 + 1; 
				
		// 3. 마지막페이지
		int endPage = startPage + 9;
		endPage = endPage > totalPage ? (totalPage > 0 ? totalPage : 1) : endPage;

		pageInfo.setListCount(listCount);
		pageInfo.setPage(p);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		req.setAttribute("boardList", boardList);
		req.setAttribute("pageInfo", pageInfo);
		
		ActionForward forward = new ActionForward();
		forward.setPath(String.format("/board/board_list.jsp?p=%s&f=%s&q=%s", p, f, q));
		return forward;
	}
}
