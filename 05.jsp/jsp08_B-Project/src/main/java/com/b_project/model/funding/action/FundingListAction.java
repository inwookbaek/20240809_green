package com.b_project.model.funding.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.PageInfo;
import com.b_project.model.funding.model.FundingBean;
import com.b_project.model.funding.service.FundingListService;

public class FundingListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ArrayList<FundingBean> fundingList = null;
		int pagerLimit = 10;
		int limit = 15;
		int page = 1;
		String id = req.getParameter("id");
		if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));
		
		FundingListService fundingListService = new FundingListService();
		int listCount = fundingListService.getListCount(id);
		fundingList = fundingListService.getFundingList(page, limit, id);
		
		int maxPage = (listCount - 1) / limit + 1;
		int startPage = ((page - 1) / pagerLimit) * pagerLimit + 1;
		int endPage = startPage + pagerLimit - 1;
		
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("fundingList", fundingList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/view/funding/fundingList.jsp");
		
		return forward;
	}

}
