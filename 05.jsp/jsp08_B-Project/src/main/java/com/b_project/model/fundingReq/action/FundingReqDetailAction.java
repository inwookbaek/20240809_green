package com.b_project.model.fundingReq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.fundingReq.model.FundingReq;
import com.b_project.model.fundingReq.service.FundingReqDetailService;

public class FundingReqDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		
		int req_no = Integer.parseInt(req.getParameter("req_no"));
		String page = req.getParameter("page");
		if(page==null) {
			page = "1";
		}
			
		FundingReqDetailService reqDetailService = new FundingReqDetailService();
		FundingReq article = reqDetailService.getArticle(req_no);
		ActionForward forward = new ActionForward();
		req.setAttribute("page", page);
		req.setAttribute("article", article);
		
		forward.setPath("view/fundingReq/fundingReqView.jsp");	
		return forward;
	}

}
