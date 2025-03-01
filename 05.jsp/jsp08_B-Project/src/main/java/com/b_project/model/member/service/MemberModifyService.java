package com.b_project.model.member.service;

import java.sql.Connection;

import com.b_project.model.member.dao.MemberDAO;
import com.b_project.model.member.model.Member;
import com.b_project.util.JDBCUtil;

public class MemberModifyService {

	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public boolean modifyMember(Member member) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isUpdateSuccess = false;
		int updateCount = memberDAO.updateMember(conn, member);
		
		if(updateCount > 0) {
			JDBCUtil.commit(conn);
			isUpdateSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isUpdateSuccess;
	}
}
