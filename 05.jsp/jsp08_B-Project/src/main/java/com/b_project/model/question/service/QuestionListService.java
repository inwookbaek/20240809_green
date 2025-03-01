package com.b_project.model.question.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.b_project.model.question.dao.QuestionDAO;
import com.b_project.model.question.model.Question;
import com.b_project.util.JDBCUtil;

public class QuestionListService {

	private QuestionDAO questionDAO = QuestionDAO.getInstance();
	
	public int getListCount(String searchOption, String searchWord, String faq, String id) {
		
		Connection conn = JDBCUtil.getConnection();
		int listCount = questionDAO.selectListCount(conn, searchOption, searchWord, faq, id);
		JDBCUtil.close(conn);
		return listCount;
	}
	
	public ArrayList<Question> getArticleList(int page, int limit, String searchOption, String searchWord, String faq, String id) {
		
		Connection conn = JDBCUtil.getConnection();
		ArrayList<Question> articleList = null;
		articleList = questionDAO.selectArticleList(conn, page, limit, searchOption, searchWord, faq, id);
		JDBCUtil.close(conn);
		return articleList;
	}
}
