package com.b_project.model.project.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.b_project.model.project.dao.ProjectDAO;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.util.JDBCUtil;

public class ProjectWriteService {

	private ProjectDAO projectDAO = ProjectDAO.getInstance();
	
	public boolean registerProject(ProjectBoardBean item) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isWriteSuccess = false;
		int insertCount = projectDAO.insertArticle(conn, item);
		
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
