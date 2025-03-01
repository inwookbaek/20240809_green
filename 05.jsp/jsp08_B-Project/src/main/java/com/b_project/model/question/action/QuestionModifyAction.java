package com.b_project.model.question.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.question.model.Question;
import com.b_project.model.question.service.QuestionModifyService;
import com.b_project.model.question.service.QuestionReadService;

public class QuestionModifyAction implements Action {
	
	private static final String FORM_VIEW = "/view/question/questionModifyForm.jsp";

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
		
		QuestionModifyService questionModifyService = new QuestionModifyService();
		ActionForward forward = new ActionForward();
		
		int q_no = Integer.parseInt(req.getParameter("q_no"));
		boolean cantChange = Boolean.parseBoolean(req.getParameter("cantchange"));
		
		Question question = new Question();
		question.setqNo(q_no);
		question.setSubject(req.getParameter("subject"));
		question.setContent(req.getParameter("content"));
		question.setCantChange(cantChange);
		
		boolean isUpdateSuccess = questionModifyService.modifyQuestion(question);
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 수정에 실패했습니다!\\n답변이 등록된 글은 수정할 수 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/questionRead.do?q_no=" + q_no);
		}
		return forward;
	}

}
