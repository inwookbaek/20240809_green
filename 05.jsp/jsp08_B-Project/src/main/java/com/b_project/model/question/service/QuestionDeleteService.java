package com.b_project.model.question.service;

import java.sql.Connection;

import com.b_project.model.question.dao.QuestionDAO;
import com.b_project.model.question.model.Question;
import com.b_project.util.JDBCUtil;

public class QuestionDeleteService {

	public boolean removeArticle(int q_no) {
		
		boolean isRemoveSuccess = false;
		
		Connection conn = JDBCUtil.getConnection();
		QuestionDAO questionDAO = QuestionDAO.getInstance();
		Question question = questionDAO.selectArticle(conn, q_no);
		
//		답변이 등록된 글은 삭제할 수 없음
		if(question.isCantChange()) {
			JDBCUtil.close(conn);
			return isRemoveSuccess;
		}
		
		int deleteCount = questionDAO.deleteArticle(conn, q_no);
		
		if(deleteCount > 0) {
			JDBCUtil.commit(conn);
			isRemoveSuccess = true;
		} else {
			JDBCUtil.rollback(conn);
		}
		JDBCUtil.close(conn);
		return isRemoveSuccess;
	}
}
