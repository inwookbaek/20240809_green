package com.b_project.model.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.question.model.Answer;
import com.b_project.model.question.model.Question;
import com.b_project.model.question.service.AnswerReadService;
import com.b_project.model.question.service.QuestionReadService;

public class QuestionReadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int q_no = Integer.parseInt(req.getParameter("q_no"));
		String page = req.getParameter("page");
		if(page==null) {
			page = "1";
		}
		
		QuestionReadService questionReadService = new QuestionReadService();
		Question article = questionReadService.getArticle(q_no);
		AnswerReadService answerReadService = new AnswerReadService();
		Answer answer = answerReadService.getArticle(q_no);
		
		ActionForward forward = new ActionForward();
		req.setAttribute("page", page);
		req.setAttribute("article", article);
		req.setAttribute("answer", answer);
		forward.setPath("/view/question/questionDetail.jsp");
		return forward;
	}

}
