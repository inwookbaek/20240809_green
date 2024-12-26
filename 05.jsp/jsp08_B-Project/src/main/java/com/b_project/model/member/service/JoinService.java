package com.b_project.model.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.b_project.model.member.dao.MemberDAO;
import com.b_project.model.member.model.Member;
import com.b_project.util.JDBCUtil;

public class JoinService {

	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public void join(JoinRequest joinReq) {
		
		Connection conn = null;
		
		try {
			conn = JDBCUtil.getConnection();
			
			Member member = memberDAO.selectByID(conn, joinReq.getId());
			if(member != null) {
				JDBCUtil.rollback(conn);
				throw new DuplicatedIdException();
			}
			
			memberDAO.insertMember(conn, new Member(joinReq.getId(), joinReq.getName(), joinReq.getPassword(), joinReq.getTel(), joinReq.geteMail()));
			JDBCUtil.commit(conn);
		} finally {
			JDBCUtil.close(conn);
		}
	}
	
}
