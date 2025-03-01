package com.lec.ex02_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BoardDAOImpl implements BoardDAOService {
	
	private BoardVO intputBoard() {
		BoardVO board = new BoardVO();
		
		String subject = JOptionPane.showInputDialog("글제목을 입력하세요!!");
		String writer = JOptionPane.showInputDialog("작성자를 입력하세요!!");
		String content = JOptionPane.showInputDialog("글상세내용을 입력하세요!!");
		
		board.setSubject(subject);
		board.setWriter(writer);
		board.setContent(content);
		return board;
	}
	
	@Override
	public void createBoard() {
		// System.out.println("글쓰기성공!!!");
		BoardVO board = intputBoard();
		// System.out.println(board.toString());
		ConnectionFactory cf = new ConnectionFactory();
		Connection conn = cf.getConnection();
		PreparedStatement pstmt = null;
		String sql = cf.getInsert();
		// System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			int row = pstmt.executeUpdate();
			System.out.println(row + "건이 성공적으로 등록되었습니다!!");			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// dummy
			}
		}
		
	}

	@Override
	public ArrayList<BoardVO> listBoard() {
		// System.out.println("게시글 전체 목록 조회 성공!!!");
		
		ArrayList<BoardVO> boardList = new ArrayList<BoardVO>();
		
		ConnectionFactory cf = new ConnectionFactory();
		Connection conn = cf.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = cf.getSelect() 
				+ " where rownum between ? and ? "
				+ " order by bno desc";
		// System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 10);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBno(rs.getInt(1));
				board.setSubject(rs.getString(2));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setCrtdate(rs.getString("crtdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				boardList.add(board);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// dummy
			}
		}		
		
		return boardList;
	}

	@Override
	public BoardVO viewBoard(int bno) {
		// System.out.println("게시글 조회 성공!!!");
		BoardVO board = new BoardVO();
		
		ConnectionFactory cf = new ConnectionFactory();
		Connection conn = cf.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = cf.getSelect() + " where bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));				
			} else {
				System.out.println(String.format("글번호(%d) 게시글이 존재하지 않습니다!!", bno));
			}
		
		} catch (SQLException e) {
			System.out.println("게시글조회 실패!!!!");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// dummy
			}
		}			
		return board;
	}

	@Override
	public void updateBoard(int bno) {
		// System.out.println("게시글 수정 성공!!!");
		BoardVO board = viewBoard(bno);
		
		String subject = JOptionPane.showInputDialog("수정할 글제목을 입력하세요!!", board.getSubject());
		String content = JOptionPane.showInputDialog("수정할 글내용을 입력하세요!!", board.getContent());
		
		ConnectionFactory cf = new ConnectionFactory();
		Connection conn = cf.getConnection();
		PreparedStatement pstmt = null;
		int row = 0;
		
		// update=update board set subject=?, content=? where bno=?		
		String sql = cf.getUpdate();	
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, bno);
			row = pstmt.executeUpdate();			
			System.out.println(String.format("글번호(" + bno + ") 게시글이 성공적으로 수정되었습니다!!"));
		} catch (SQLException e) {
			System.out.println("게시글 수정 실패!!!!");
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// dummy
			}
		}			
		
	}

	@Override
	public void deleteBoard(int bno) {
		// System.out.println("게시글 삭제 성공!!!");
		
		ConnectionFactory cf = new ConnectionFactory();
		Connection conn = cf.getConnection();
		PreparedStatement pstmt = null;
		String sql = cf.getDelete();
		int row = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			row = pstmt.executeUpdate();			
			System.out.println(String.format("글번호(" + bno + ") 게시글이 성공적으로 삭제되었습니다!!"));
		} catch (SQLException e) {
			System.out.println("게시글 삭제 실패!!!!");
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// dummy
			}
		}		
		
	}

	@Override
	public ArrayList<BoardVO> findBySubjectBoard(String subject) {
		// System.out.println("게시글 조회(제목) 성공!!!");
		ArrayList<BoardVO> boardList = new ArrayList<BoardVO>();
		
		ConnectionFactory cf = new ConnectionFactory();
		Connection conn = cf.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = cf.getSelect() 
				+ " where subject like '%" + subject + "%'"
				+ " order by bno desc";		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBno(rs.getInt(1));
				board.setSubject(rs.getString(2));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setCrtdate(rs.getString("crtdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				boardList.add(board);
			}
		
		} catch (SQLException e) {
			System.out.println("게시글 목록(by 제목) 조회 실패!!!");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// dummy
			}
		}		
	
		return boardList;
	}

	@Override
	public ArrayList<BoardVO> findByWriterBoard(String writer) {
		// System.out.println("게시글 조회(작성자) 성공!!!");
		ArrayList<BoardVO> boardList = new ArrayList<BoardVO>();
		
		ConnectionFactory cf = new ConnectionFactory();
		Connection conn = cf.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = cf.getSelect() 
				+ " where writer like '%" + writer + "%'"
				+ " order by bno desc";		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBno(rs.getInt(1));
				board.setSubject(rs.getString(2));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setCrtdate(rs.getString("crtdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				boardList.add(board);
			}
		
		} catch (SQLException e) {
			System.out.println("게시글 목록(by 작성자) 조회 실패!!!");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// dummy
			}
		}		
	
		return boardList;
	}

}
