package com.b_project.model.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.member.model.Member;
import com.b_project.model.member.service.MemberModifyService;

public class MemberModifyAction implements Action {

	private static final String FORM_VIEW = "/view/member/memberModifyForm.jsp";
	
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

	private ActionForward processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		MemberModifyService memberModifyService = new MemberModifyService();
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String eMail = req.getParameter("eMail");
		
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		member.setTel(tel);
		member.seteMail(eMail);
		
		boolean isUpdateSuccess = memberModifyService.modifyMember(member);
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('회원 정보 수정에 실패했습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('회원 정보가 변경되었습니다!')");
			out.println("location.href='" + req.getContextPath() + "/memberDetail.do?id=" + id + "'");
			out.println("</script>");
		}
		return null;
	}
}
