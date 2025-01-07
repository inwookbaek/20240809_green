package com.b_project.model.member.service;

import java.sql.Connection;

import com.b_project.model.member.dao.MemberDAO;
import com.b_project.util.JDBCUtil;

public class MemberDeleteService {
	
	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public boolean removeMember(String id) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isRemoveSuccess = false;
		int deleteCount = memberDAO.deleteMember(conn, id);
		
		if(deleteCount > 0) {
			JDBCUtil.commit(conn);
			isRemoveSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isRemoveSuccess;
	}

}
