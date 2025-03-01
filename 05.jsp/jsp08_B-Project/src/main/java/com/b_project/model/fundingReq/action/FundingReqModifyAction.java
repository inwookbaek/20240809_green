package com.b_project.model.fundingReq.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.fundingReq.model.FundingReq;
import com.b_project.model.fundingReq.service.FundingReqDetailService;
import com.b_project.model.fundingReq.service.FundingReqModifyService;
import com.b_project.model.member.model.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FundingReqModifyAction implements Action {
	
	private static final String FORM_VIEW = "/view/fundingReq/fundingReqModifyForm.jsp";

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}		
	}

	private ActionForward processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		int req_no = Integer.parseInt(req.getParameter("req_no"));
		String nowPage = req.getParameter("page");
		FundingReqDetailService fundingDetailService = new FundingReqDetailService();
		FundingReq article = fundingDetailService.getArticle(req_no);
		req.setAttribute("page", nowPage);
		req.setAttribute("article", article);
		forward.setPath(FORM_VIEW);
		return forward;
	}

	private ActionForward processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		FundingReqModifyService projectModifyService = new FundingReqModifyService();
		ActionForward forward = new ActionForward();
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		req.setAttribute("errors", errors);
		
		//
		String realFolder = null;
		String saveFolder = "openFundingFile";
		int fileSize = 1024 * 1024 * 5;
		ServletContext context = req.getServletContext();
		
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(req, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
		int req_no = Integer.parseInt(multi.getParameter("req_no"));
		int nowPage = Integer.parseInt(multi.getParameter("page"));
		
		// String nowPage = multi.getParameter("page");
		// int req_no = Integer.parseInt(req.getParameter("req_no"));
		
		FundingReq item = new FundingReq();
		item.setReqNo(req_no);
		item.setSubject(multi.getParameter("subject"));
		item.setId(((Member) req.getSession().getAttribute("authUser")).getId()); // ????????
		item.setContent(multi.getParameter("content"));
		
		item.setFile(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		boolean isUpdateSuccess = projectModifyService.modifyFunding(item);
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 수정 실패입니다.')");
			out.println("history.back()");	
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/fundingReqList.do?page=" + nowPage);

		}
		return forward;
	}
}
