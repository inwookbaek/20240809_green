package com.b_project.model.member.service;

import java.sql.Connection;

import com.b_project.model.member.dao.MemberDAO;
import com.b_project.model.member.model.Member;
import com.b_project.util.JDBCUtil;

public class MemberDetailService {

	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public Member getMember(String id) {
		
		Connection conn = JDBCUtil.getConnection();
		Member member = memberDAO.selectByID(conn, id);
		JDBCUtil.close(conn);
		return member;
	}
}
