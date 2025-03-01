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
import com.b_project.model.review.model.ReviewBoardBean;
import com.b_project.model.review.service.ReviewModifyService;
import com.b_project.model.review.service.ReviewPageService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewModifyAction implements Action  {

	private static final String FORM_VIEW = "/view/review/review_modify.jsp";

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
		int review_no = Integer.parseInt(req.getParameter("review_no"));
		ReviewPageService reviewPageService = new ReviewPageService();
		ReviewBoardBean article = reviewPageService.getArticle(review_no);
		req.setAttribute("article", article);
		forward.setPath(FORM_VIEW);
		return forward;
	}

	private ActionForward processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ReviewModifyService reviewModifyService = new ReviewModifyService();
		ActionForward forward = new ActionForward();
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		req.setAttribute("errors", errors);
		
		String realFolder = null;
		String saveFolder = "reviewFile";
		int fileSize = 1024 * 1024 * 5; // 5MB
		ServletContext context = req.getServletContext();
		
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(req, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
		
		int review_no = Integer.parseInt(multi.getParameter("review_no"));
		
		ReviewBoardBean item = new ReviewBoardBean();
		item.setReviewNo(review_no);
		item.setReviewSubject(multi.getParameter("review_subject"));
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		
		item.setReviewContent(multi.getParameter("review_content"));
		
		String review_category = multi.getParameter("review_category");
		if(review_category == null || review_category.isEmpty()) {
			item.setReviewCategory("기타");
		} else {
			item.setReviewCategory(multi.getParameter("review_category"));
		}
		
		item.setReviewFile(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		
		boolean isUpdateSuccess = reviewModifyService.modifyReview(item);
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 수정 실패!!')");
			out.println("history.back()");	// history.back()는 이전 페이지로의 이동을 뜻함
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/review.do?review_no=" + review_no);
		}	
		return forward;
	}

}

