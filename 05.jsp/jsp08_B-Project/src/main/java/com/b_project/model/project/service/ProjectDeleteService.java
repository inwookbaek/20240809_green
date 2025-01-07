package com.b_project.model.project.service;

import java.sql.Connection;

import com.b_project.model.project.dao.ProjectDAO;
import com.b_project.util.JDBCUtil;

public class ProjectDeleteService {

	private ProjectDAO projectDAO = ProjectDAO.getInstance();
	
	public boolean removeArticle(int project_no) {
		
		Connection conn = JDBCUtil.getConnection();
		boolean isRemoveSuccess = false;
		int deleteCount = projectDAO.deleteArticle(conn, project_no);
		
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
