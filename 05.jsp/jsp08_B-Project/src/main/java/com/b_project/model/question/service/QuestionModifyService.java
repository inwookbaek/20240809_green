package com.b_project.model.question.service;

import java.sql.Connection;

import com.b_project.model.question.dao.QuestionDAO;
import com.b_project.model.question.model.Question;
import com.b_project.util.JDBCUtil;

public class QuestionModifyService {
	
	private QuestionDAO questionDAO = QuestionDAO.getInstance();
	
	public boolean modifyQuestion(Question question) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isUpdateSuccess = false;
		
//		답변이 등록된 글은 수정할 수 없음
		if(question.isCantChange()) {
			JDBCUtil.close(conn);
			return isUpdateSuccess;
		}
		
		int updateCount = questionDAO.updateArticle(conn, question);
		
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
