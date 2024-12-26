package com.b_project.model.project.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.project.service.ProjectDeleteService;

public class ProjectDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ProjectDeleteService projectDeleteService = new ProjectDeleteService();
		ActionForward forward = new ActionForward();
		int project_no = Integer.parseInt(req.getParameter("project_no"));
		
		boolean isDeleteSuccess = projectDeleteService.removeArticle(project_no);
		
		if(!isDeleteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 삭제에 실패했습니다!');");
			out.println("history.back();");
			out.println("</script>");	
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/projectDisplay.do");
		}
		return forward;
	}

}
