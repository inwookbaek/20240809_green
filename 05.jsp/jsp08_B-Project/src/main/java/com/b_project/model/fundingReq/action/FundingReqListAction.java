package com.b_project.model.fundingReq.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.PageInfo;
import com.b_project.model.fundingReq.model.FundingReq;
import com.b_project.model.fundingReq.service.FundingReqListService;

public class FundingReqListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		
		ArrayList<FundingReq> articleList = new ArrayList<FundingReq>();
		int page = 1;
		int limit = 10;
		
		if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));
		
		FundingReqListService fundingListService = new FundingReqListService();
		int listCount = fundingListService.getListCount();
		articleList = fundingListService.getArticleList(page, limit);
		
		int maxPage = (int)((double)listCount/limit + 0.95); // 페이지올림	

		// 현재페이지에 보여줄 시작 페이지수(1, 11, 21....)
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재페이지에 보여줄 마지작 페이지수(10, 20, 30...)
		int endPage = startPage + 10 - 1;

		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("articleList", articleList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/view/fundingReq/fundingReqList.jsp");

		return forward;
	}


}
