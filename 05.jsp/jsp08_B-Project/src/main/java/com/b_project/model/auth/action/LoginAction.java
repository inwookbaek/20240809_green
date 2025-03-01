package com.b_project.model.auth.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.auth.service.LoginFailException;
import com.b_project.model.auth.service.LoginService;
import com.b_project.model.member.model.Member;

public class LoginAction implements Action {
	
	private static final String FORM_VIEW = "/view/auth/loginForm.jsp";

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
		
		LoginService loginService = new LoginService();
		String id = req.getParameter("id").trim();
		String password = req.getParameter("password").trim();	
		ActionForward forward = new ActionForward();
		Map<String, Boolean> errors = new HashMap<String, Boolean>();	
		
//		loginForm.jsp에 로그인 정보를 넘겨주기 위해 지정
		req.setAttribute("errors", errors);
		
//		사용자가 id와 password를 입력하지 않았을 경우 errors에 에러를 삽입하여 FORM_VIEW로 돌아가게 한다.
		if(id == null || id.isEmpty()) errors.put("id", Boolean.TRUE);
		if(password == null || password.isEmpty()) errors.put("password", Boolean.TRUE);
		if(!errors.isEmpty()) {
			forward.setPath(FORM_VIEW);
			return forward;
		}
		
		try {
			Member member = loginService.login(id, password);
//			session속성에 로그인 정보를 지정한다.
			req.getSession().setAttribute("authUser", member);
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/view/auth/loginSuccess.jsp");
			return forward;
//		loginService에서 id나 password가 일치하지 않을 경우 LoginFailException 발생
		} catch (LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			forward.setPath(FORM_VIEW);
			return forward;
		}	
	}
}



	


