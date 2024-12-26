package com.b_project.model.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;

import com.b_project.model.review.service.ReviewDeleteService;

public class ReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ReviewDeleteService reviewDeleteService = new ReviewDeleteService();
		ActionForward forward = new ActionForward();
		int review_no = Integer.parseInt(req.getParameter("review_no"));
		
		boolean isDeleteSuccess = reviewDeleteService.removeArticle(review_no);
		
		if(!isDeleteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 삭제에 실패했습니다!');");
			out.println("history.back();");
			out.println("</script>");	
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/review_list.do");
		}	
		return forward;
	}

}
