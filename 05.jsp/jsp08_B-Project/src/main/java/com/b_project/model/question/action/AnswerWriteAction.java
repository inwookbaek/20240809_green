package com.b_project.model.question.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.question.model.Answer;
import com.b_project.model.question.model.Question;
import com.b_project.model.question.service.AnswerWriteService;
import com.b_project.model.question.service.QuestionReadService;

public class AnswerWriteAction implements Action {

	private static final String FORM_VIEW = "/view/question/answerWriteForm.jsp";
	
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
		req.setAttribute("article", article);
		forward.setPath(FORM_VIEW);
		return forward;
	}

	private ActionForward processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		AnswerWriteService answerWriteService = new AnswerWriteService();
		ActionForward forward = new ActionForward();
		
		Answer answer = new Answer();
		
		int q_no = Integer.parseInt(req.getParameter("q_no"));
		answer.setqNo(q_no);
		
		answer.setContent(req.getParameter("content"));
		
		boolean isWriteSuccess = answerWriteService.registerAnswer(answer);
		
		if(!isWriteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 등록에 실패했습니다!')");
			out.println("history.back()");	
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/questionRead.do?q_no=" + q_no);
		}
		return forward;
	}

}
