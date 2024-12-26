package com.b_project.model.question.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.question.model.Answer;
import com.b_project.model.question.model.Question;
import com.b_project.model.question.service.AnswerModifyService;
import com.b_project.model.question.service.AnswerReadService;
import com.b_project.model.question.service.QuestionReadService;

public class AnswerModifyAction implements Action {

	private static final String FORM_VIEW = "/view/question/answerModifyForm.jsp";

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
		int q_no = Integer.parseInt(req.getParameter("q_no"));
		QuestionReadService questionReadService = new QuestionReadService();
		Question article = questionReadService.getArticle(q_no);
		AnswerReadService answerReadService = new AnswerReadService();
		Answer answer = answerReadService.getArticle(q_no);
		req.setAttribute("article", article);
		req.setAttribute("answer", answer);
		forward.setPath(FORM_VIEW);
		return forward;
	}

	private ActionForward processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		AnswerModifyService answerModifyService = new AnswerModifyService();
		ActionForward forward = new ActionForward();
		
		int q_no = Integer.parseInt(req.getParameter("q_no"));
		
		Answer answer = new Answer();
		answer.setqNo(q_no);
		answer.setContent(req.getParameter("content"));
		
		boolean isUpdateSuccess = answerModifyService.modifyAnswer(answer);
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 수정에 실패했습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/questionRead.do?q_no=" + q_no);
		}
		return forward;
	}

}
