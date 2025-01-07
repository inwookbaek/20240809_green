package com.b_project.model.auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.b_project.model.member.dao.MemberDAO;
import com.b_project.model.member.model.Member;
import com.b_project.util.JDBCUtil;

public class LoginService {

	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public Member login(String id, String password) {
			
		try(Connection conn = JDBCUtil.getConnection()) {
			Member member = memberDAO.selectByID(conn, id);
//			DB에 ID가 존재하지 않을 경우 Exception 발생
			if(member == null) {
				throw new LoginFailException();
			}
//			DB상의 password와 일치하지 않을 경우 Exception 발생
			if(!member.matchPassword(password)) {
				throw new LoginFailException();
			}
			return member;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}	
	}
}
