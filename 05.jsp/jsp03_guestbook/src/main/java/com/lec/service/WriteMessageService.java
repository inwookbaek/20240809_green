package com.lec.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.lec.dao.MessageDAO;
import com.lec.jdbc.ConnectionProvider;
import com.lec.jdbc.JDBCUtility;
import com.lec.model.MessageDTO;

public class WriteMessageService {

	private static WriteMessageService instance = new WriteMessageService();
	private WriteMessageService() {}
	public static WriteMessageService getInstance() {
		return instance;
	}
	
	public void write(MessageDTO message) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			MessageDAO messageDAO = MessageDAO.getInstance();
			messageDAO.insert(conn, message);
		} catch (SQLException e) {
			throw new RuntimeException("방명록등록실패!! " + e.getMessage());
		} finally {
			JDBCUtility.close(conn, null, null);		
		}
	}
}
