package com.b_project.model.member.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.b_project.model.member.dao.MemberDAO;
import com.b_project.model.member.model.Member;
import com.b_project.util.JDBCUtil;

public class MemberListService {

	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public int getListCount(String searchOption, String searchWord) {
		
		Connection conn = JDBCUtil.getConnection();
		int listCount = memberDAO.selectListCount(conn, searchOption, searchWord);
		JDBCUtil.close(conn);
		return listCount;
	}
	
	public ArrayList<Member> getArticleList(int page, int limit, String searchOption, String searchWord) {
		
		Connection conn = JDBCUtil.getConnection();
		ArrayList<Member> memberList = memberDAO.selectMemberList(conn, page, limit, searchOption, searchWord);
		JDBCUtil.close(conn);
		return memberList;
	}
}
