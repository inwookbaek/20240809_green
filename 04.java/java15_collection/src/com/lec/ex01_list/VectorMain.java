package com.lec.ex01_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VectorMain {

	public static void main(String[] args) {
		// 1. ArrayList - Member
		List<Member> members = new ArrayList<Member>();
		members.add(new Member("1", "12345", "홍길동", "hong1@gmail.com"));
		members.add(new Member("2", "12345", "홍길순", "hong2@gmail.com"));
		members.add(new Member("3", "12345", "홍길녀", "hong3@gmail.com"));
		members.add(new Member("4", "12345", "홍길상", "hong4@gmail.com"));
		members.add(new Member("5", "12345", "홍길자", "hong5@gmail.com"));
		
		for(Member member:members) {
			System.out.println(member.toString());
		}
		System.out.println();
		
		// 2. Vector - Board
		// 츄가 : insert into board ....
		Vector<Board> boards = new Vector<Board>();
		boards.add(new Board(1, "제목1", "홍길동", "글 상세내용1...."));
		boards.add(new Board(2, "제목2", "홍길순", "글 상세내용2...."));
		boards.add(new Board(3, "제목3", "홍길녀", "글 상세내용3...."));
		boards.add(new Board(4, "제목4", "홍길상", "글 상세내용4...."));
		boards.add(new Board(5, "제목5", "홍길자", "글 상세내용5...."));
		for(Board board:boards) {
			System.out.println(board.toString());
		}
		System.out.println();
		
		// 삭제 : delete from board where bno = 3;
		boards.remove(3);
		
		// 조회 : select * from board;
		System.out.println("<table border=\"1\">");
		for(int i=0;i<boards.size();i++) {
			Board board = boards.get(i);
			// html출력한다면?
			System.out.println("<tr>"
					+ "<td>" + board.bno + "</td>"
					+ "<td>" + board.title + "</td>"
					+ "<td>" + board.writer + "</td>"
					+ "<td>" + board.content + "</td>"
					+ "</tr>");
		}
		System.out.println("</table>");
	}

}

class Member {
	
	private String id;
	private String pw;
	private String name;
	private String email;
	
	public Member(String id, String pw, String name, String email) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + "]";
	}
}

class Board {
	
	int bno;
	String title;
	String writer;
	String content;
	
	public Board(int bno, String title, String writer, String content) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + "]";
	}
}