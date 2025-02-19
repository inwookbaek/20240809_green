package com.b_project.model.question.service;

import java.sql.Connection;

import com.b_project.model.question.dao.QuestionDAO;
import com.b_project.model.question.model.Question;
import com.b_project.util.JDBCUtil;

public class QuestionReadService {

	public Question getArticle(int q_no) {
		
		Question question = null;
		Connection conn = JDBCUtil.getConnection();
		QuestionDAO questionDAO = QuestionDAO.getInstance();
		int updateCount1 = questionDAO.updateReadCount(conn, q_no);
		
		question = questionDAO.selectArticle(conn, q_no);
//		조회수가 100이 넘으면 FAQ로 등록
		if(question.getReadCNT() >= 100) {
			int updateCount2 = questionDAO.changeToFaq(conn, q_no);
			if(updateCount1 > 0 && updateCount2 > 0) JDBCUtil.commit(conn);
			else JDBCUtil.rollback(conn);
			
			JDBCUtil.close(conn);
			return question;
		}
		
		if(updateCount1 > 0) JDBCUtil.commit(conn);
		else JDBCUtil.rollback(conn);
		
		JDBCUtil.close(conn);
		return question;	
	}
}
