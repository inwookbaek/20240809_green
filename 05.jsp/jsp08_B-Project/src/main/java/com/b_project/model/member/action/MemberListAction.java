package com.b_project.model.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.PageInfo;
import com.b_project.model.member.model.Member;
import com.b_project.model.member.service.MemberListService;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ArrayList<Member> memberList = null;
		int pagerLimit = 10;
		int limit = 15;
		int page = 1;
		String searchOption = "";
		String searchWord = "";
		
		if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));
		if(req.getParameter("search-option") != null && !req.getParameter("search-option").isEmpty()) {
			searchOption = req.getParameter("search-option");
		}
		if(req.getParameter("search-word") != null && !req.getParameter("search-word").isEmpty()) {
			searchWord = req.getParameter("search-word");
		}
		
		MemberListService memberListService = new MemberListService();
		int listCount = memberListService.getListCount(searchOption, searchWord);
		memberList = memberListService.getArticleList(page, limit, searchOption, searchWord);
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
		req.setAttribute("memberList", memberList);
		req.setAttribute("search-option", searchOption);
		req.setAttribute("search-word", searchWord);
			
		ActionForward forward = new ActionForward();
		forward.setPath("/view/member/memberList.jsp");
		
		return forward;
	}
}
