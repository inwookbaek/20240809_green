package com.b_project.model.auth.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession(false);
		ActionForward forward = new ActionForward();
		
		if(session != null) session.invalidate();
		forward.setRedirect(true);
		forward.setPath(req.getContextPath() + "/index.jsp");
		
		return forward;
	}

}
