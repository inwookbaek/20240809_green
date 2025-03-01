package com.b_project.model.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.b_project.model.question.model.Question;
import com.b_project.util.JDBCUtil;

public class QuestionDAO {

	public static QuestionDAO questionDAO;
	private QuestionDAO() {}
	public static QuestionDAO getInstance() {
		if(questionDAO == null) questionDAO = new QuestionDAO();
		return questionDAO;
	}
	
	
	public int selectListCount(Connection conn, String searchOption, String searchWord, String faq, String id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		String sql = null;

//		FAQ or not
		if(id != null && !id.isEmpty()) {
//			제목으로 검색한 글의 수
			if(searchOption.equals("제목")) {
				sql = "select count(*) from question where subject like \'%" + searchWord + "%\'"
					+ " and id = '" + id + "'";
//			제목과 내용으로 검색한 글의 수
			} else if(searchOption.equals("제목과내용")) {
				sql = "select count(*) from question where (subject like \'%" + searchWord 
					+ "%\' or content like \'%" + searchWord + "%\')"
					+ " and id ='" + id + "'";
//			모든 글의 수.
			} else {
				sql = "select count(*) from question"
					+ " where id ='" + id +"'";
			}
		} else if(faq.equals("true")) {
//			제목으로 검색한 글의 수
			if(searchOption.equals("제목")) {
				sql = "select count(*) from question where subject like \'%" + searchWord + "%\'"
					+ " and faq = true";
//			제목과 내용으로 검색한 글의 수
			} else if(searchOption.equals("제목과내용")) {
				sql = "select count(*) from question where (subject like \'%" + searchWord 
					+ "%\' or content like \'%" + searchWord + "%\')"
					+ " and faq = true";
//			모든 글의 수.
			} else {
				sql = "select count(*) from question"
					+ " where faq = true";
			}
		} else {
//			제목으로 검색한 글의 수
			if(searchOption.equals("제목")) {
				sql = "select count(*) from question where subject like \'%" + searchWord + "%\'";
//			제목과 내용으로 검색한 글의 수
			} else if(searchOption.equals("제목과내용")) {
				sql = "select count(*) from question where subject like \'%" + searchWord 
						+ "%\' or content like \'%" + searchWord + "%\'";
//			모든 글의 수.
			} else {
				sql = "select count(*) from question";
			}
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

	public ArrayList<Question> selectArticleList(Connection conn, int page, int limit, String searchOption, String searchWord, String faq, String id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Question> articleList = new ArrayList<Question>();
		Question question = null;
		int startRow = (page-1) * limit;
		String sql = null;
		
//		FAQ or not
		if(id != null && !id.isEmpty()) {
//			제목으로 검색한 글
			if(searchOption.equals("제목")) {
				sql = "select q.*, m.`name` from question q left outer join member m on q.id = m.id" 
					+ " where subject like \'%" + searchWord + "%\'"
					+ " and q.id ='" + id +"'"
					+ " order by q_no desc limit ?, ?";
//			제목과 내용으로 검색한 글
			} else if(searchOption.equals("제목과내용")) {
				sql = "select q.*, m.`name`from question q left outer join member m on q.id = m.id" 
					+ " where (subject like \'%" + searchWord 
					+ "%\' or content like \'%" + searchWord + "%\')"
					+ " and q.id ='" + id + "'"
					+ " order by q_no desc limit ?, ?";
//			모든 글. selectListCount(Connection conn)의 결과값
			} else {
				sql = "select q.*, m.`name` from question q left outer join member m on q.id = m.id" 
					+ " where q.id ='" + id + "'"
					+ " order by q_no desc limit ?, ?";
			}
		} else if(faq.equals("true")) {
//			제목으로 검색한 글
			if(searchOption.equals("제목")) {
				sql = "select q.*, m.`name` from question q left outer join member m on q.id = m.id"
					+ " where subject like \'%" + searchWord + "%\'"
					+ " and faq = true"
					+ " order by q_no desc limit ?, ?";
//			제목과 내용으로 검색한 글
			} else if(searchOption.equals("제목과내용")) {
				sql = "select q.*, m.`name` from question q left outer join member m on q.id = m.id"
					+ " where (subject like \'%" + searchWord 
					+ "%\' or content like \'%" + searchWord + "%\')"
					+ " and faq = true"
					+ " order by q_no desc limit ?, ?";
//			모든 글. selectListCount(Connection conn)의 결과값
			} else {
				sql = "select q.*, m.`name` from question q left outer join member m on q.id = m.id" 
					+ " where faq = true"
					+ " order by q_no desc limit ?, ?";
			}
		} else {
//			제목으로 검색한 글
			if(searchOption.equals("제목")) {
				sql = "select q.*, m.`name` from question q left outer join member m on q.id = m.id" 
					+ " where subject like \'%" + searchWord + "%\'"
					+ " order by q_no desc limit ?, ?";
//			제목과 내용으로 검색한 글
			} else if(searchOption.equals("제목과내용")) {
				sql = "select q.*, m.`name` from question q left outer join member m on q.id = m.id"
					+ " where subject like \'%" + searchWord 
					+ "%\' or content like \'%" + searchWord + "%\'"
					+ " order by q_no desc limit ?, ?";
//			모든 글. selectListCount(Connection conn)의 결과값
			} else {
				sql = "select q.*, m.`name` from question q left outer join member m on q.id = m.id" 
					+ " order by q_no desc limit ?, ?";
			}
		}
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				question = new Question();
				questionSetter(question, rs);
				articleList.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}	
		return articleList;
	}

	public Question selectArticle(Connection conn, int q_no) {
		
		Question question = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select q.*, m.`name` from question q left outer join member m on q.id = m.id"
				   + " where q_no = " + q_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				question = new Question();
				questionSetter(question, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return question;
	}
	
	public int insertArticle(Connection conn, Question question) {
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		String sql = "insert into question(subject, id, wrt_date, mod_date, content, read_cnt, faq, cantchange) "
				   + "values(?,?,now(),now(),?,0,false,false)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, question.getSubject());
			pstmt.setString(2, question.getId());
			pstmt.setString(3, question.getContent());
			
			insertCount = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return insertCount;
	}
	
	public int insertArticleByAdmin(Connection conn, Question question) {
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		String sql = "insert into question(subject, id, wrt_date, mod_date, content, read_cnt, faq, cantchange) "
				   + "values(?,?,now(),now(),?,0,true,false)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, question.getSubject());
			pstmt.setString(2, question.getId());
			pstmt.setString(3, question.getContent());
			
			insertCount = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return insertCount;
	}
	
	public int deleteArticle(Connection conn, int q_no) {
		
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		
		String sql = "delete from question where q_no = " + q_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return deleteCount;
	}
	
	public int updateArticle(Connection conn, Question question) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update question set subject=?, mod_date=now(), content=? "
					+"where q_no = " + question.getqNo();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, question.getSubject());
			pstmt.setString(2, question.getContent());
						
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	public int updateReadCount(Connection conn, int q_no) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		String sql = "update question set read_cnt = read_cnt + 1 where q_no = " + q_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	
	void questionSetter(Question question, ResultSet rs) throws SQLException {
		
		question.setqNo(rs.getInt("q_no"));
		question.setSubject(rs.getString("subject"));
		question.setId(rs.getString("id"));
		question.setWrtDate(rs.getTimestamp("wrt_date"));
		question.setModDate(rs.getTimestamp("mod_date"));
		question.setContent(rs.getString("content"));
		question.setReadCNT(rs.getInt("read_cnt"));
		question.setFaq(rs.getBoolean("faq"));
		question.setCantChange(rs.getBoolean("cantchange"));
		question.setName(rs.getString("name"));
	}
	
	public int changeToFaq(Connection conn, int q_no) {
		
		PreparedStatement pstmt = null;
		String sql = "update question set faq = true where q_no = " + q_no;
		int updateCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return updateCount;
	}
	
	public int changeCantChange(Connection conn, boolean cantChange, int q_no) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		String sql = "update question set cantchange = ? where q_no =" + q_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, cantChange);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return updateCount;
	}
}
