package com.b_project.model.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.auth.service.LoginService;
import com.b_project.model.member.dao.MemberDAO;
import com.b_project.model.member.model.Member;
import com.b_project.model.member.service.DuplicatedIdException;
import com.b_project.model.member.service.JoinRequest;
import com.b_project.model.member.service.JoinService;
import com.b_project.util.AES128;

public class KaKaoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String id = req.getParameter("id");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		
		AES128 encoder = new AES128("b-projectByMslim");
		String password = encoder.encrypt(req.getParameter("id"));
		String confirmPassword = password;
			
		JoinService joinService = new JoinService();
		JoinRequest joinRequest = new JoinRequest(id, nickname, password, confirmPassword, null, email);
		ActionForward forward = new ActionForward();
		LoginService loginService = new LoginService();
		
		try {
			joinService.join(joinRequest);
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/view/auth/loginSuccess.jsp");
			return forward;
		} catch (DuplicatedIdException e) {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/view/auth/loginSuccess.jsp");
			return forward;
		} finally {
			Member user = loginService.login(id, password);
			req.getSession().setAttribute("authUser", user);
		}
	}

}
