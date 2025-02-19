package com.b_project.model.question.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.question.service.AnswerDeleteService;

public class AnswerDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		AnswerDeleteService answerDeleteService = new AnswerDeleteService();
		ActionForward forward = new ActionForward();
		int q_no = Integer.parseInt(req.getParameter("q_no"));
		
		boolean isDeleteSuccess = answerDeleteService.removeArticle(q_no);
		
		if(!isDeleteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 삭제에 실패했습니다!');");
			out.println("history.back();");
			out.println("</script>");	
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/questionList.do");
		}
		return forward;
	}
}
