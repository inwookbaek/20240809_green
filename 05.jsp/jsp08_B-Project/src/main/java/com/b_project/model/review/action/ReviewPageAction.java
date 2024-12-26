package com.b_project.model.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.review.model.ReviewBoardBean;
import com.b_project.model.review.service.ReviewPageService;

public class ReviewPageAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int review_no = Integer.parseInt(req.getParameter("review_no"));
		String page = req.getParameter("page");
		if(page==null) {
			page = "1";
		}

		ReviewPageService reviewPageService = new ReviewPageService();
		
		ReviewBoardBean article = reviewPageService.getArticle(review_no);

		ActionForward forward = new ActionForward();
		req.setAttribute("page", page);
		req.setAttribute("article", article);
		forward.setPath("view/review/review_page.jsp");

		return forward;
	}

}
