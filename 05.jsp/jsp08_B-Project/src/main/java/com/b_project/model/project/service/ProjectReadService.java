package com.b_project.model.project.service;

import java.sql.Connection;

import com.b_project.model.project.dao.ProjectDAO;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.util.JDBCUtil;

public class ProjectReadService {

	private ProjectDAO projectDAO = ProjectDAO.getInstance();
	
	public ProjectBoardBean getArticle(int project_no) {
		
		Connection conn = JDBCUtil.getConnection();
		ProjectBoardBean boardBean = null;
		int updateCount = projectDAO.updateReadCount(conn, project_no);
		
		
		if(updateCount > 0) JDBCUtil.commit(conn);
		else JDBCUtil.rollback(conn);
		
		boardBean = projectDAO.selectArticle(conn, project_no);
		JDBCUtil.close(conn);
		return boardBean;
	}
}
