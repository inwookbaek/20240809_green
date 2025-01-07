package com.b_project.model.question.service;

import java.sql.Connection;

import com.b_project.model.question.dao.AnswerDAO;
import com.b_project.model.question.model.Answer;
import com.b_project.util.JDBCUtil;

public class AnswerModifyService {
	
	private AnswerDAO answerDAO = AnswerDAO.getInstance();
	
	public boolean modifyAnswer(Answer answer) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isUpdateSuccess = false;
		
		int updateCount = answerDAO.updateArticle(conn, answer);
		
		if(updateCount > 0) {
			JDBCUtil.commit(conn);
			isUpdateSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isUpdateSuccess;
	}
}
