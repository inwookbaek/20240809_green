package com.b_project.model.question.service;

import java.sql.Connection;

import com.b_project.model.question.dao.AnswerDAO;
import com.b_project.model.question.dao.QuestionDAO;
import com.b_project.model.question.model.Answer;
import com.b_project.util.JDBCUtil;

public class AnswerWriteService {

	private AnswerDAO answerDAO = AnswerDAO.getInstance();
	private QuestionDAO questionDAO = QuestionDAO.getInstance();
	
	public boolean registerAnswer(Answer answer) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isWriteSuccess = false;
		int insertCount = answerDAO.insertArticle(conn, answer);
		int updateCount = questionDAO.changeCantChange(conn, true, answer.getqNo());
		
		if(insertCount > 0 && updateCount > 0) {
			JDBCUtil.commit(conn);
			isWriteSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isWriteSuccess;
	}
}
