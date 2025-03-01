package com.b_project.model.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.model.project.service.ProjectReadService;

public class ProjectReadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int project_no = Integer.parseInt(req.getParameter("project_no"));
		String page = req.getParameter("page");
		if(page==null) {
			page = "1";
		}
		
		ProjectReadService projectReadService = new ProjectReadService();
		ProjectBoardBean article = projectReadService.getArticle(project_no);
		ActionForward forward = new ActionForward();
		req.setAttribute("page", page);
		req.setAttribute("article", article);
		forward.setPath("/view/project/projectDetail.jsp");
		return forward;
	}

}
