package com.b_project.model.question.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.member.model.Member;
import com.b_project.model.question.model.Question;
import com.b_project.model.question.service.QuestionWriteService;

public class QuestionWriteAction implements Action {

	private static final String FORM_VIEW = "/view/question/questionWriteForm.jsp";
	
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
		
		QuestionWriteService questionWriteService = new QuestionWriteService();
		ActionForward forward = new ActionForward();
		boolean isWriteSuccess = false;
		Question question = new Question();
		question.setSubject(req.getParameter("subject"));
		
		Member user = (Member)req.getSession(false).getAttribute("authUser");
		String id = user.getId();
		String name = user.getName();
		
		question.setId(id);
		question.setName(name);
		question.setContent(req.getParameter("content"));
		
		if(id.equals("admin")) {
			isWriteSuccess = questionWriteService.registerQuestionByAdmin(question);
		} else {
			isWriteSuccess = questionWriteService.registerQuestion(question);
		}
		
		if(!isWriteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 등록에 실패했습니다!')");
			out.println("history.back()");	
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/questionList.do?id=" + id);
		}
		return forward;
	}

}
