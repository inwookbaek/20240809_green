package com.b_project.model.project.service;

import java.sql.Connection;

import com.b_project.model.project.dao.ProjectDAO;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.util.JDBCUtil;

public class ProjectModifyService {

	private ProjectDAO projectDAO = ProjectDAO.getInstance();
	
	public boolean modifyProject(ProjectBoardBean item) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isUpdateSuccess = false;
		int updateCount = projectDAO.updateArticle(conn, item);
		
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
