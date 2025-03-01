package com.b_project.model.question.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.question.service.QuestionDeleteService;

public class QuestionDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		QuestionDeleteService questionDeleteService = new QuestionDeleteService();
		ActionForward forward = new ActionForward();
		int q_no = Integer.parseInt(req.getParameter("q_no"));
		
		boolean isDeleteSuccess = questionDeleteService.removeArticle(q_no);
		
		if(!isDeleteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 삭제에 실패했습니다!\\n답변이 등록된 글은 삭제할 수 없습니다!');");
			out.println("history.back();");
			out.println("</script>");	
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/questionList.do");
		}
		return forward;
	}

}
