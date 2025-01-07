package com.b_project.model.project.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import com.b_project.model.project.dao.ProjectDAO;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.util.JDBCUtil;

public class ProjectListService {
	
	private ProjectDAO projectDAO = ProjectDAO.getInstance();
	
	public int getListCount(String searchOption, String searchWord) {
		
		Connection conn = JDBCUtil.getConnection();
		int listCount = projectDAO.selectListCount(conn, searchOption, searchWord);
		JDBCUtil.close(conn);
		return listCount;
	}
	
	public int getListCount(String searchOption, String searchWord, boolean finishedOrNot) {
		
		Connection conn = JDBCUtil.getConnection();
		int listCount = projectDAO.selectListCount(conn, searchOption, searchWord, finishedOrNot);
		JDBCUtil.close(conn);
		return listCount;
	}
	
	public ArrayList<ProjectBoardBean> getArticleList(int page, int limit, String orderBy, boolean asc) {
		
		return getArticleList(page, limit, orderBy, asc, "", "");
	}
	
	public ArrayList<ProjectBoardBean> getArticleList(int page, int limit, String orderBy, boolean asc, String searchOption, String searchWord) {
		
		Connection conn = JDBCUtil.getConnection();
		ArrayList<ProjectBoardBean> articleList = null;
		articleList = projectDAO.selectArticleList(conn, page, limit, orderBy, asc, searchOption, searchWord);
		JDBCUtil.close(conn);
		return articleList;
	}
	
	public ArrayList<ProjectBoardBean> getArticleList(int page, int limit, String orderBy, boolean asc, String searchOption, String searchWord, boolean finishedOrNot) {
		
		Connection conn = JDBCUtil.getConnection();
		ArrayList<ProjectBoardBean> articleList = null;
		articleList = projectDAO.selectArticleList(conn, page, limit, orderBy, asc, searchOption, searchWord, finishedOrNot);
		JDBCUtil.close(conn);
		return articleList;
	}
}
