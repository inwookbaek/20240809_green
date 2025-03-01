package com.b_project.model.review.dao;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.b_project.model.review.model.ReviewBoardBean;
import com.b_project.util.JDBCUtil;


public class ReviewDAO {

//	싱글톤	
	private static ReviewDAO reviewDAO;
	private ReviewDAO() {}
	public static ReviewDAO getInstance() {
		if(reviewDAO == null) reviewDAO = new ReviewDAO();
		return reviewDAO;
	}
	
	
	// 리뷰 수
	public int selectlistCount(Connection conn, String searchOption, String searchWord) {
		

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		String sql = null;

//		제목으로 검색한 글의 수
		if(searchOption.equals("제목")) {
			sql = "select  count(*) from review where review_subject like \'%" + searchWord + "%\'";
			
//		제목과 내용으로 검색한 글의 수
		} else if(searchOption.equals("제목과내용")) {
			sql = "select count(*) from review where review_subject like \'%" + searchWord 
					+ "%\' or review_content like \'%" + searchWord + "%\'";
//		모든 글의 수.
		} else {
			sql =  "select count(*) from review";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);			
		} catch (Exception e) {
			System.out.println("리뷰 가져오기 실패" + e);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return listCount;
		
	}
	
	// 리뷰 목록 조회
	
	public ArrayList<ReviewBoardBean> selectArticleList(Connection conn, int page, int limit, String orderBy, boolean asc) {
		
		return selectArticleList(conn, page, limit, orderBy, asc, "", "");
	}
	
	
	
	public ArrayList<ReviewBoardBean> selectArticleList(Connection conn, int page, int limit, String orderBy, boolean asc,  String searchOption, String searchWord) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewBoardBean> reviewList = new ArrayList<ReviewBoardBean>();
		ReviewBoardBean review = null;
		int startRow = (page-1) * limit;
		String sort = null;
		String sql = null;
		
		if(asc) sort = "asc";
		else sort="desc";
		
//		제목으로 검색한 글의 수
		if(searchOption.equals("제목")) {
			sql = "select * from review where review_subject like \'%" + searchWord 
					+ "%\' order by " + orderBy + " " + sort + " limit ?, ?";
//		제목과 내용으로 검색한 글의 수
		} else if(searchOption.equals("제목과내용")) {
			sql = "select * from review where review_subject like \'%" + searchWord + "%\' or content like \'%" + searchWord 
					+ "%\' order by " + orderBy + " " + sort + " limit ?, ?";
//		모든 글의 수.
		} else {
			sql = "select * from review order by " + orderBy + " " + sort + " limit ?, ?"; 
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				review = new ReviewBoardBean();
				boardBeanSetter(review, rs);
				reviewList.add(review);
			}
		} catch (Exception e) {
			System.out.println("리뷰목록 조회 실패" + e);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return reviewList;
	}
	
	// 리뷰등록
	public int insertReview(Connection conn, ReviewBoardBean item) {
		
		PreparedStatement pstmt = null;
		int insertCount=0;
		
		String sql = "insert into review(review_no, review_id, review_subject, file, " + 
				"review_content, review_readcount, review_date, review_category) values(?,?,?,?,?,?,now(),?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item.getReviewNo());
			pstmt.setString(2, item.getReviewId());
			pstmt.setString(3, item.getReviewSubject());
			pstmt.setString(4, item.getReviewFile());
			pstmt.setString(5, item.getReviewContent());
			pstmt.setInt(6, 0);
			
			if(item.getReviewCategory() == null || item.getReviewCategory().isEmpty()) {
				pstmt.setString(7, "기타");
			} else {
				pstmt.setString(7, item.getReviewCategory());
			}
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return insertCount;		
	}

	
	// 리뷰 내용보기
	
	public ReviewBoardBean selectArticle(Connection conn, int review_no) {
		
		ReviewBoardBean bean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review where review_no = " + review_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean = new ReviewBoardBean();
				boardBeanSetter(bean, rs);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return bean;
		
	}
	private void boardBeanSetter(ReviewBoardBean boardBean, ResultSet rs)  throws SQLException {
		
		boardBean.setReviewNo(rs.getInt("review_no"));
		boardBean.setReviewSubject(rs.getString("review_subject"));
		boardBean.setReviewDate(rs.getDate("review_date"));
		boardBean.setReviewContent(rs.getString("review_content"));		
		boardBean.setReviewCategory(rs.getString("review_category"));
		boardBean.setReviewReadCount(rs.getInt("review_readCount"));
		boardBean.setReviewFile(rs.getString("file"));
		boardBean.setReviewId(rs.getString("review_id"));
		
		
		
		
	}
	public int updateReadCount(Connection conn, int review_no) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		String sql = "update review set review_readCount = review_readCount + 1 where review_no = " + review_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return updateCount;
	}
	
	// 리뷰수정
	public int updateArticle(Connection conn, ReviewBoardBean item) {

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = null;
		
		
		try {
			
			if(item.getReviewFile() != null) {
				sql = "update review set review_subject = ?, "						
						+ "review_content = ?, "	
						+ "review_category = ?, "						
						+ "review_no = ? ,"
						+ "file = ? "
						+ "where review_no = " + item.getReviewNo();	
			} else {
				sql = "update review set review_subject = ?, "
						+ "review_content = ?, "						
						+ "review_category = ?, "
						+ "review_no = ? "
						+ "where review_no = " + item.getReviewNo();
			}
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item.getReviewSubject());
			pstmt.setString(2, item.getReviewContent());
				
			if(item.getReviewCategory()==null || item.getReviewCategory().isEmpty()) {
				pstmt.setString(3, "기타");
			} else {
				pstmt.setString(3, item.getReviewCategory());
			}
			pstmt.setInt(4, item.getReviewNo());
			
			if(item.getReviewFile() != null) {
				pstmt.setString(5, item.getReviewFile());
			}
						
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	
	// 리뷰 삭제
	
	public int deleteArticle(Connection conn, int review_no) {
		
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		
		String sql = "delete from review where review_no = " + review_no;
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
