package com.lec.ex02_board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BoardMenu {
	
	private double ver;
	
	public BoardMenu(double ver) {
		this.ver = ver;
	}
	
	public void mainMenu() throws Exception {
		
		mainTextMenu();
		
		BoardDAOImpl bddao = BoardFactory.getInstance();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		while(true) {
			switch((char)br.read()) {
			case '1': bddao.createBoard(); break;
			case '2': bddao.listBoard(); break;
			case '3': bddao.viewBoard(1); break;
			case '4': bddao.updateBoard(1); break;
			case '5': bddao.deleteBoard(1); break;
			case '6': bddao.findBySubjectBoard(""); break;
			case '7': bddao.findByWriterBoard(""); break;
			case '0': System.out.println("프로그램종료!!!"); System.exit(0);
			}
		}
	}

	public void mainTextMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("***** 게시판프로그램 V(" + ver + ") *****\n\n");
		sb.append("1. 새로운 글쓰기\n");
		sb.append("2. 게시글 전체 조회\n");
		sb.append("3. 게시글 보기\n");
		sb.append("4. 게시글 수정\n");
		sb.append("5. 게시글 삭제\n");
		sb.append("6. 게시글 조회(제목)\n");
		sb.append("7. 게시글 조회(작성자)\n");
		sb.append("0. 작업종료\n");
		sb.append("========================\n\n");
		sb.append("처리할 메뉴번호를 입력하세요!!\n");
		System.out.println(sb.toString());
	}

	public void mainBoardMenu() {
		
		BoardDAOImpl bddao = BoardFactory.getInstance();
	
		while(true) {
			int menuNo = mainJOptionPanMenu();
			
			switch(menuNo) {
			case 1: makeBoard(bddao); break;
			case 2: listBoardJOptionPan(bddao); break;
//			case 2: listBoard(bddao); break;
			case 3: contentView(bddao); break;
			case 4: updateBoard(bddao); break;
			case 5: deleteBoard(bddao); break;
			case 6: findBySubject(bddao); break;
			case 7: findByWriter(bddao); break;
			case 0: System.out.println("프로그램종료!!!"); System.exit(0);
			}
		}
		
	}

	private void findByWriter(BoardDAOImpl bddao) {
		// 실습. 코드완성하기
		// // "select * from board where writer like '%홍길%'"
		String writer = JOptionPane.showInputDialog("검색할 작성자을 입력하세요!!");
		if((writer==null) || (writer.equals(""))) {
			return;
		} else {
			ArrayList<BoardVO> boards = bddao.findByWriterBoard(writer);
			
			String boardList = "=".repeat(60) + "\n" 
					  + "글번호\t\t제목\t\t작성자\t\t글상세내용\n"
				      + "=".repeat(60) + "\n";
				
				for(BoardVO board:boards) {
					boardList += board.toString() + "\n";
				}
				JOptionPane.showConfirmDialog(null, boardList);				
		}
	}

	private void findBySubject(BoardDAOImpl bddao) {
		// "select * from board where subject like '%제목%'"
		String subject = JOptionPane.showInputDialog("검색할 글제목을 입력하세요!!");
		if((subject==null) || (subject.equals(""))) {
			return;
		} else {
			ArrayList<BoardVO> boards = bddao.findBySubjectBoard(subject);
			
			String boardList = "=".repeat(60) + "\n" 
					  + "글번호\t\t제목\t\t작성자\t\t글상세내용\n"
				      + "=".repeat(60) + "\n";
				
				for(BoardVO board:boards) {
					boardList += board.toString() + "\n";
				}
				JOptionPane.showConfirmDialog(null, boardList);				
		}
	}

	private void deleteBoard(BoardDAOImpl bddao) {
		String bno = JOptionPane.showInputDialog("삭제할 글번호를 입력하세요!!");
		if((bno==null) || (bno.equals(""))) {
			return;
		} else {
			bddao.deleteBoard(Integer.parseInt(bno));
		}
	}		

	private void updateBoard(BoardDAOImpl bddao) {
		String bno = JOptionPane.showInputDialog("수정할 글번호를 입력하세요!!");
		if((bno==null) || (bno.equals(""))) {
			return;
		} else {
			bddao.updateBoard(Integer.parseInt(bno));
		}
	}		
		

	private void contentView(BoardDAOImpl bddao) {
		String bno = JOptionPane.showInputDialog("조회할 글번호를 입력하세요!!");
		if((bno==null) || (bno.equals(""))) {
			return;
		} else {
			BoardVO board = bddao.viewBoard(Integer.parseInt(bno));
			
			JOptionPane.showConfirmDialog(null,
					"제  목 : " + board.getSubject() + "\n"
				  + "작성자 : " + board.getWriter() + "\n"
				  + "글내용 : " + board.getContent());
		}
	}

	private void listBoard(BoardDAOImpl bddao) {
		ArrayList<BoardVO> boards = bddao.listBoard();
		
		System.out.println("=".repeat(60));
		System.out.println("글번호\t\t제목\t\t작성자\t\t글상세내용");
		System.out.println("=".repeat(60));
		
		for(BoardVO board:boards) {
			System.out.println(board.toString());
		}
		System.out.println("----------- 게시글목록조회 종료 ----------");
	}
	
	private void listBoardJOptionPan(BoardDAOImpl bddao) {
		ArrayList<BoardVO> boards = bddao.listBoard();
		
		String boardList = "=".repeat(60) + "\n" 
			  + "글번호\t\t제목\t\t작성자\t\t글상세내용\n"
		      + "=".repeat(60) + "\n";
		
		for(BoardVO board:boards) {
			boardList += board.toString() + "\n";
		}
		JOptionPane.showConfirmDialog(null, boardList);	
	}

	private void makeBoard(BoardDAOImpl bddao) {
		bddao.createBoard();	
	}

	public int mainJOptionPanMenu() {
		
		String menuNo = JOptionPane.showInputDialog(
			"***** 게시판프로그램 V(" + ver + ") *****\n\n" +
			"1. 새로운 글쓰기\n" +
			"2. 게시글 전체 조회\n" +
			"3. 게시글 보기\n" +
			"4. 게시글 수정\n" +
			"5. 게시글 삭제\n" + 
			"6. 게시글 조회(제목)\n" +
			"7. 게시글 조회(작성자)\n" +
			"0. 작업종료\n" +
			"========================\n\n" +
			"처리할 메뉴번호를 입력하세요!!\n"
		);
		if((menuNo == null) || (menuNo.equals(""))) {
			return 0;
		} else {
			return Integer.parseInt(menuNo);			
		}
	}
}











