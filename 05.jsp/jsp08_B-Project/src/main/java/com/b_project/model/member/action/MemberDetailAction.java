package com.b_project.model.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.member.model.Member;
import com.b_project.model.member.service.MemberDetailService;

public class MemberDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String id = req.getParameter("id");
		
		MemberDetailService detailService = new MemberDetailService();
		Member member = detailService.getMember(id);
		ActionForward forward = new ActionForward();
		req.setAttribute("member", member);
		forward.setPath("/view/member/memberDetail.jsp");
		return forward;
	}

}
