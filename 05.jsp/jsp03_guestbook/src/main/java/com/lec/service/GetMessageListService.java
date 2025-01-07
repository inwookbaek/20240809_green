package com.lec.service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.lec.dao.MessageDAO;
import com.lec.jdbc.ConnectionProvider;
import com.lec.jdbc.JDBCUtility;
import com.lec.model.ListMessageDTO;
import com.lec.model.MessageDTO;

public class GetMessageListService {

	private static GetMessageListService instance = new GetMessageListService();
	private GetMessageListService() {}
	public static GetMessageListService getIntance() {
		return instance;
	}
	
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	
	
	public ListMessageDTO getMessageList(int pageNumber) {
		
		Connection conn = null;
		int currentPage = pageNumber;
		
		try {
			conn = ConnectionProvider.getConnection();
			MessageDAO messageDAO = MessageDAO.getInstance();
			
			int messageTotalCount = messageDAO.selectCount(conn);
			
			List<MessageDTO> listMessage = null;
			int firstRow = 0;
			int endRow = 0;
			
			if(messageTotalCount > 0) {
				firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE;
				listMessage = messageDAO.selectList(conn, firstRow, endRow);
			} else {
				currentPage = 0;
				listMessage = Collections.emptyList();
			}
			
			return new ListMessageDTO(listMessage, messageTotalCount, currentPage
					, MESSAGE_COUNT_PER_PAGE, firstRow, endRow);
		} catch (Exception e) {
			throw new ServiceException("방명록 목록 조회 실패!!! " + e.getMessage(), e);
		} finally {
			JDBCUtility.close(conn, null, null);
		}
	}
}
