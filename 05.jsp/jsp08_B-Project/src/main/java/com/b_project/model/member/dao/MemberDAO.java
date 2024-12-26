package com.b_project.model.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import com.b_project.model.member.model.Member;
import com.b_project.util.JDBCUtil;

public class MemberDAO {

	// 싱글톤
	public static MemberDAO memberDAO;	
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(memberDAO == null) memberDAO = new MemberDAO();
		return memberDAO;
	}
	

	public int selectListCount(Connection conn, String searchOption, String searchWord) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		String sql = null;
		
		if(searchOption.equals("아이디")) {
			sql = "select count(*) from member where id like \'%" + searchWord + "%\'";
		} else if(searchOption.equals("작성자")) {
			sql = "select count(*) from member where name like \'%" + searchWord + "%\'";
		} else {
			sql = "select count(*) from member";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Member> selectMemberList(Connection conn, int page, int limit, String searchOption, String searchWord) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Member> memberList = new ArrayList<Member>();
		Member member = null;
		int startRow = (page-1) * limit;
		String sql = null;
		
		if(searchOption.equals("아이디")) {
			sql = "select * from member where id like \'%" + searchWord + "%\' order by regdate desc limit ?, ?";
		} else if(searchOption.equals("작성자")) {
			sql = "select * from member where name like \'%" + searchWord + "%\' order by regdate desc limit ?, ?";
		} else {
			sql = "select * from member order by regdate desc limit ?, ?";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new Member(rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						toDate(rs.getTimestamp(6)), 
						rs.getInt(7));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}	
		return memberList;
	}
	
	
	public Member selectByID(Connection conn, String id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		String sql = "select * from member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new Member(rs.getString(1), 
									rs.getString(2), 
									rs.getString(3), 
									rs.getString(4), 
									rs.getString(5), 
									toDate(rs.getTimestamp(6)), 
									rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return member;
	}
	
	public int insertMember(Connection conn, Member member) {
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		// sql문을 보면 등급(level)의 default값은 1임을 알 수 있다.
		String sql = "insert into member values(?,?,?,?,?,now(),1)";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			
		
				pstmt.setString(4, member.getTel());
			
			
			pstmt.setString(5, member.geteMail());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return insertCount;
	}
	
	public int deleteMember(Connection conn, String id) {
		
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		
		String sql = "delete from member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return deleteCount;
	}
	
	public int updateMember(Connection conn, Member member) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update member set name = ?, tel = ?, email = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getTel());
			pstmt.setString(3, member.geteMail());
			pstmt.setString(4, member.getId());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
	
}
