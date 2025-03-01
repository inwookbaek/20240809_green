package com.b_project.model.review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.review.model.ReviewBoardBean;
import com.b_project.model.review.model.ReviewPageInfo;
import com.b_project.model.review.service.ReviewListService;

public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ArrayList<ReviewBoardBean> articleList = null;
		int page = 1;
		int pagerLimit = 10;
		int limit = 15;
		
		executeHelper(req, articleList, pagerLimit, limit);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/view/review/review_list.jsp");
		
		return forward;
	}

	public static void executeHelper(HttpServletRequest req, ArrayList<ReviewBoardBean> articleList, int pagerLimit, int limit) {
		int page = 1;
		String searchOption = "";
		String searchWord = "";
		String orderBy = "";
		boolean asc = false;
		
		if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));
		if(req.getParameter("search-option") != null && !req.getParameter("search-option").isEmpty()) {
			searchOption = req.getParameter("search-option");
		}
		if(req.getParameter("search-word") != null && !req.getParameter("search-word").isEmpty()) {
			searchWord = req.getParameter("search-word");
		}
		if(req.getParameter("orderBy") != null && !req.getParameter("orderBy").isEmpty()) {
			orderBy = req.getParameter("orderBy");
		}
		if(req.getParameter("asc") != null && !req.getParameter("asc").isEmpty()) {
			asc = Boolean.parseBoolean(req.getParameter("asc"));
		}
		
		
		ReviewListService listReviewService = new ReviewListService();
		int listCount = listReviewService.getListCount(searchOption, searchWord);
		if(orderBy.isEmpty()) {
			articleList = listReviewService.getArticleList(page, limit, "review_no", false, searchOption, searchWord);
		} else {
			articleList = listReviewService.getArticleList(page, limit, orderBy, asc, searchOption, searchWord);
		}
		
		
		int maxPage = (listCount - 1) / limit + 1;
		int startPage = ((page - 1) / pagerLimit) * pagerLimit + 1;
		int endPage = startPage + pagerLimit - 1;
		
		if(endPage > maxPage) endPage = maxPage; 
		
		ReviewPageInfo pageInfo = new ReviewPageInfo();
		pageInfo.setPage(page);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("articleList",	articleList);
		req.setAttribute("search-option", searchOption);
		req.setAttribute("search-word", searchWord);
		req.setAttribute("orderBy", orderBy);
		req.setAttribute("asc", asc);
	}
}
