package com.b_project.model.question.service;

import java.sql.Connection;

import com.b_project.model.question.dao.AnswerDAO;
import com.b_project.model.question.dao.QuestionDAO;
import com.b_project.util.JDBCUtil;

public class AnswerDeleteService {
	
	public boolean removeArticle(int q_no) {
		
		boolean isRemoveSuccess = false;
		
		Connection conn = JDBCUtil.getConnection();
		AnswerDAO answerDAO = AnswerDAO.getInstance();
		QuestionDAO questionDAO = QuestionDAO.getInstance();

		int deleteCount = answerDAO.deleteArticle(conn, q_no);
		int updateCount = questionDAO.changeCantChange(conn, false, q_no);
		
		if(deleteCount > 0 && updateCount > 0) {
			JDBCUtil.commit(conn);
			isRemoveSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isRemoveSuccess;
	}
}
