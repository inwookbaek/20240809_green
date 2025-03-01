package com.lec.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.lec.board.vo.BoardVO;
import com.lec.db.JDBCUtility;

public class BoardDAO {

	// Singleton
	private static BoardDAO boardDao;
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if(boardDao == null) boardDao = new BoardDAO();
		return boardDao;
	}
	
	Connection conn = null;
	DataSource ds = null;
	
	// DB Connection
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	// 1. 글쓰기
	public int insert(BoardVO board) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into board(bno, writer, pass, subject, content, file, "
				   + " re_ref, re_lev, re_seq, readcount, crtdate) "
				   + " values(?,?,?,?,?,?,?,?,?,?,now())";
		int insertCount = 0;
		int bno = 0;
		
		try {
			pstmt = conn.prepareStatement("select max(bno) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) bno = rs.getInt(1) + 1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getPass());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5, board.getContent());
			pstmt.setString(6, board.getFile());
			pstmt.setInt(7, bno);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글등록실패!!!");
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		return insertCount;
	}
	
	// 2. 글갯수구하기
	public int selectListCount(String f, String q) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = "select count(*) from board "
				   + " where " + f + " like ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + q + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) listCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시글갯수조회실패!!! " + e.getMessage());
		} finally {
			JDBCUtility.close(conn, pstmt, rs);
		}
		
		return listCount;
	}	
	
	// 3. 글목록조회하기
	public List<BoardVO> selectBoardList(int p, int l, String f, String q) {
		
		BoardVO board = null;
		List<BoardVO> boardList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board "
				   + " where " + f + " like ?"
				   + " order by re_ref desc, re_seq asc "
				   + " limit ?, " + l;
		
		int startRow = (p-1) * l;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + q + "%");
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardVO();
				board.setBno(rs.getInt("bno"));
				board.setPass(rs.getString("pass"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setFile(rs.getString("file"));
				board.setRe_ref(rs.getInt("re_ref"));
				board.setRe_lev(rs.getInt("re_lev"));
				board.setRe_seq(rs.getInt("re_seq"));
				board.setReadcount(rs.getInt("readcount"));
				board.setCrtdate(rs.getDate("crtdate"));
				boardList.add(board);
			}
		} catch (Exception e) {
			System.out.println("게시글목록조회실패!!! " + e.getMessage());
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		return boardList;	
	}

	// 4. 글조회수증가하기
	
	public int updateReadCount(int bno) {
		int readCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update board set readcount = readcount + 1 "
				   + " where bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			readCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글조회수 증가 실패!!! " + e.getMessage());
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}	
		return readCount;
	}

	// 5. 글상세보기
	public BoardVO selectBoard(int bno) {
		
		BoardVO board = new BoardVO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where bno = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setBno(bno);
				board.setWriter(rs.getString("writer"));
				board.setPass(rs.getString("pass"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setFile(rs.getString("file"));
				board.setRe_ref(rs.getInt("re_ref"));
				board.setRe_lev(rs.getInt("re_lev"));
				board.setRe_seq(rs.getInt("re_seq"));
				board.setReadcount(rs.getInt("readcount"));
				board.setCrtdate(rs.getDate("crtdate"));
			}
		} catch (Exception e) {
			System.out.println("게시글 상세보기 실패!!! " + e.getMessage());
		} finally {
			JDBCUtility.close(conn, pstmt, rs);
		}
		return board;
	}
	
	// 6. 글작성자확인하기
	public boolean isBoardWriter(int bno, String pass) {
		
		boolean isWriter = false;
		
		BoardVO board = new BoardVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where bno = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			rs.next();
			if(pass.equals(rs.getString("pass"))) isWriter = true;
		} catch (Exception e) {
			System.out.println("게시글조회실패!!! " + e.getMessage());
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		return isWriter;
	}

	// 7. 글수정하기
	public int updateBoard(BoardVO board) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update board set subject = ?, content = ?, file = ? "
				   + " where bno = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getFile());
			pstmt.setInt(4, board.getBno());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글 수정 실패!!! " + e.getMessage());
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
		
		return updateCount;
	}
	
	// 8. 글삭제하기
	public int deleteBoard(int bno) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "delete from board where bno = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글 삭제 실패!!! " + e.getMessage());
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}			
		return deleteCount;
	}
	
	// 9. 댓글작성하기
	public int insertReplyBoard(BoardVO board) {
		
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update board set re_seq = re_seq + 1 "
				   + " where re_ref = ? and re_seq > ?";
		
		int bno = 0;
		int re_ref = board.getRe_ref();
		int re_lev = board.getRe_lev();
		int re_seq = board.getRe_seq();
		
		try {
			pstmt = conn.prepareStatement("select max(bno) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) bno = rs.getInt(1) + 1;
			
			// 원글수정
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			int updateCount = pstmt.executeUpdate();
			if(updateCount > 0) JDBCUtility.commit(conn);
			
			// 댓글등록
			re_lev += 1;
			re_seq += 1;
			sql = "insert into board(bno, writer, pass, subject, content, file, "
				+ " re_ref, re_lev, re_seq, readcount, crtdate) "
				+ " values(?,?,?,?,?,?,?,?,?,?,now())";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getPass());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5, board.getContent());
			pstmt.setString(6, "");
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);
			insertCount = pstmt.executeUpdate();		
		} catch (Exception e) {
			System.out.println("댓글등록실패!!! "+ e.getMessage());
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		return insertCount;
	}
}
