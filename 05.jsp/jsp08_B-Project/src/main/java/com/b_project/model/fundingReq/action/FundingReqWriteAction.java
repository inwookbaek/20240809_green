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
import com.b_project.model.fundingReq.service.FundingReqWriteService;
import com.b_project.model.member.model.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FundingReqWriteAction implements Action {
	
	private static final String FORM_VIEW = "/view/fundingReq/fundingReqWriteForm.jsp";

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

	private ActionForward processForm(HttpServletRequest req, HttpServletResponse res) {
		
		ActionForward forward = new ActionForward();
		forward.setPath(FORM_VIEW);
		return forward;
	}

	private ActionForward processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		FundingReqWriteService fundingReqWriteService = new FundingReqWriteService();
		ActionForward forward = new ActionForward();
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		req.setAttribute("errors", errors);
		
		String realFolder = null;
		String saveFolder = "attachedFile";
		int fileSize = 1024 * 1024 * 5; // 5MB
		ServletContext context = req.getServletContext();
		
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(req, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
		FundingReq item = new FundingReq();
		item.setSubject(multi.getParameter("subject"));
		item.setId(((Member)req.getSession().getAttribute("authUser")).getId());
		item.setContent(multi.getParameter("content"));
		item.setFile(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		
		boolean isWriteSuccess = fundingReqWriteService.registerProject(item);
		
		if(!isWriteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 등록 실패!!')");
			out.println("history.back()");	// history.back()는 이전 페이지로의 이동을 뜻함
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/fundingReqList.do");
		}
		return forward;
	}
}
