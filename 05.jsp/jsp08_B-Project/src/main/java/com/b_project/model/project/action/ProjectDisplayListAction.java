package com.b_project.model.project.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.model.project.service.ProjectListService;

public class ProjectDisplayListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ArrayList<ProjectBoardBean> articleList = null;
		int pagerLimit = 10;
		int limit = 12;
		
		ProjectListAction.executeHelper(req, articleList, pagerLimit, limit);
			
		ActionForward forward = new ActionForward();
		forward.setPath("/view/project/projectDisplayList.jsp");
		
		return forward;
	}
}
