package com.b_project.model.fundingReq.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.fundingReq.service.FundingReqDeleteService;

public class FundingReqDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		FundingReqDeleteService fundingReqDeleteService = new FundingReqDeleteService();
		ActionForward forward = new ActionForward();
		int req_no = Integer.parseInt(req.getParameter("req_no"));
		
		boolean isDeleteSuccess = fundingReqDeleteService.removearticle(req_no);
		
		if(!isDeleteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 삭제 실패');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/fundingReqList.do");
		}		
		return forward;
	}

}
