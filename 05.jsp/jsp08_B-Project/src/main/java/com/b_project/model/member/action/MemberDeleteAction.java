package com.b_project.model.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.member.model.Member;
import com.b_project.model.member.service.MemberDeleteService;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		MemberDeleteService memberDeleteService = new MemberDeleteService();
		ActionForward forward = new ActionForward();
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		Member user = (Member)req.getSession(false).getAttribute("authUser");
		boolean isDeleteSuccess = false;
		
		if(user.idStartsWithNumber()) {
			if(user.matchEmail(password)) {
				isDeleteSuccess = memberDeleteService.removeMember(id);
			}
		} else {
			if(user.matchPassword(password)) {
				isDeleteSuccess = memberDeleteService.removeMember(id);
			}
		}
		
		if(!isDeleteSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('회원 탈퇴에 실패했습니다!\\n비밀번호를 확인하세요!');");
			out.println("history.back();");
			out.println("</script>");	
		} else {
			forward.setRedirect(true);
			if(user.getId().equals("admin")) {
				forward.setPath(req.getContextPath() + "/memberList.do");
			} else {
				forward.setPath(req.getContextPath() + "/logout.do");
			}
		}
		return forward;
	}

}
