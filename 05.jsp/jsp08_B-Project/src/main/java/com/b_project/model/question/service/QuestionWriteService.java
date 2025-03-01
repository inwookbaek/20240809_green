package com.b_project.model.question.service;

import java.sql.Connection;

import com.b_project.model.question.dao.QuestionDAO;
import com.b_project.model.question.model.Question;
import com.b_project.util.JDBCUtil;

public class QuestionWriteService {

	private QuestionDAO questionDAO = QuestionDAO.getInstance();
	
	public boolean registerQuestion(Question question) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isWriteSuccess = false;
		int insertCount = questionDAO.insertArticle(conn, question);
		
		if(insertCount > 0) {
			JDBCUtil.commit(conn);
			isWriteSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isWriteSuccess;
	}
	
//	admin이 쓴 질문은 무조건 FAQ
	public boolean registerQuestionByAdmin(Question question) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isWriteSuccess = false;
		int insertCount = questionDAO.insertArticleByAdmin(conn, question);
		
		if(insertCount > 0) {
			JDBCUtil.commit(conn);
			isWriteSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isWriteSuccess;
	}
}
