package com.b_project.model.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.member.service.DuplicatedIdException;
import com.b_project.model.member.service.JoinRequest;
import com.b_project.model.member.service.JoinService;

public class JoinAction implements Action {

	private static final String FORM_VIEW = "/view/member/joinForm.jsp";
	
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

	private ActionForward processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		JoinService joinService = new JoinService();
		JoinRequest joinRequest = new JoinRequest(req.getParameter("id").trim(), 
												  req.getParameter("name").trim(),
												  req.getParameter("password").trim(),
												  req.getParameter("confirmPassword").trim(),
												  req.getParameter("tel").trim(),			
												  req.getParameter("eMail").trim());
		ActionForward forward = new ActionForward();
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		req.setAttribute("errors", errors);
		joinRequest.validate(errors);
		
		if(!errors.isEmpty()) {
			forward.setPath(FORM_VIEW);
			return forward;
		}
		
		try {
			joinService.join(joinRequest);
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/view/member/joinSuccess.jsp");
			return forward;
		} catch (DuplicatedIdException e) {
			errors.put("duplicatedId", Boolean.TRUE);
			forward.setPath(FORM_VIEW);
			return forward;
		}
	}
}


