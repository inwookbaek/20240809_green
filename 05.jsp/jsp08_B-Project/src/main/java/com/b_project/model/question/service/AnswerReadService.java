package com.b_project.model.question.service;

import java.sql.Connection;

import com.b_project.model.question.dao.AnswerDAO;
import com.b_project.model.question.model.Answer;
import com.b_project.util.JDBCUtil;

public class AnswerReadService {

	public Answer getArticle(int q_no) {
		
		Answer answer = null;
		Connection conn = JDBCUtil.getConnection();
		AnswerDAO answerDAO = AnswerDAO.getInstance();
		
		answer = answerDAO.selectArticle(conn, q_no);
		JDBCUtil.close(conn);
		return answer;
	}
	
	

}
