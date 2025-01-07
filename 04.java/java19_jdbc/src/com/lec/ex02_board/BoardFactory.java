package com.lec.ex02_board;

public class BoardFactory {
	// DB를 연결
	// BoardDAOImpl -> ConnectionFactory
	// DAO객체는 외부에서 생성하지 못하도록 싱글톤객체로 생성
	private BoardFactory() {}
	private static BoardDAOImpl bddao = null;
	public static BoardDAOImpl getInstance() {
		if(bddao == null) bddao = new BoardDAOImpl();
		return bddao;
	};
}
