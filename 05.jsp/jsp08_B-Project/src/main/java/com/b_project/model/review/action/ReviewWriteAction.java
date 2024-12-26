package com.b_project.model.review.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.member.model.Member;
import com.b_project.model.review.model.ReviewBoardBean;
import com.b_project.model.review.service.ReviewWriteService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewWriteAction implements Action{
	
	private static final String FORM_VIEW = "/view/review/review_writeForm.jsp";
	
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
	

	private ActionForward processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		ReviewWriteService writeReviewService = new ReviewWriteService();
		ActionForward forward = new ActionForward();
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		req.setAttribute("errors", errors);
		
		String realFolder = null;
		String saveFolder = "reviewFile";
		int fileSize = 1024 * 1024 * 5; // 5MB
		ServletContext context = req.getServletContext();
		
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(req, realFolder,fileSize, "utf-8", new DefaultFileRenamePolicy());
		
		ReviewBoardBean item = new ReviewBoardBean();
		item.setReviewSubject(multi.getParameter("review_subject"));
		item.setReviewId(((Member)req.getSession().getAttribute("authUser")).getId());
		item.setReviewContent(multi.getParameter("review_content"));
		
		String category = multi.getParameter("review_category");
		
		if(category == null || category.isEmpty()) {
			item.setReviewCategory("기타");
		} else	{
			item.setReviewCategory(multi.getParameter("review_category"));
		} 
		
		item.setReviewFile(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		
		
		boolean isWriteSuccess = writeReviewService.registerReview(item);
		
		if(!isWriteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alter('리뷰 등록 싪패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/review_list.do");
		}
		
		return forward;	
	}
}
