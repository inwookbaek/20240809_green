package com.lec.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.lec.dao.MessageDAO;
import com.lec.jdbc.ConnectionProvider;
import com.lec.jdbc.JDBCUtility;
import com.lec.model.MessageDTO;

public class UpdateMessageService {

	private static UpdateMessageService instance = new UpdateMessageService();
	private UpdateMessageService() {}
	public static UpdateMessageService getInstance() {
		return instance;
	}
	
	public void updateMessage(int id, String password, String msg) {
		Connection conn = null;
		
		try {		
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);	
			
			MessageDAO messageDAO = MessageDAO.getInstance();
			MessageDTO message = messageDAO.select(conn, id);			
			
			if(message == null) {
				throw new MessageNotFoundException("방명록 메시지가 없습니다!!");
			}
			
			if(!message.matchPassword(password)) {
				throw new InvalidPasswordException("비밀번호가 틀립니다. 다시 입력하세요!!");
			}			
			
			int row = messageDAO.update(conn, id, msg);
			conn.commit();
		} catch (SQLException e) {
			JDBCUtility.rollback(conn);
			throw new ServiceException("방명록 수정 실패!!! " + e.getMessage(), e);	
		} catch (MessageNotFoundException | InvalidPasswordException e) {
			JDBCUtility.rollback(conn);
			throw e;
		} finally {
			JDBCUtility.close(conn, null, null);
		}			
	}
}
