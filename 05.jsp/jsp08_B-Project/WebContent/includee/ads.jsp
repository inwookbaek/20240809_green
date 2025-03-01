<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.b_project.util.JDBCUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.b_project.model.project.dao.ProjectDAO"%>
<%@page import="com.b_project.model.project.model.ProjectBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

${ctxPath = pageContext.request.contextPath; ''}

<%
	Connection conn = JDBCUtil.getConnection();
	ProjectDAO projectDAO = ProjectDAO.getInstance();
	ArrayList<ProjectBoardBean> articleList = new ArrayList<ProjectBoardBean>();
	int maxNo = projectDAO.getMaxProjectNo(conn);
	int compareNo = -1;
	int i = 0;
	
	if(projectDAO.selectListCount(conn, "", "")>=2) {
		while(i<2) {
			int project_no = (int)(Math.random()*maxNo + 1);
			ProjectBoardBean ad = projectDAO.selectArticle(conn, project_no);
			if(ad == null) {
				continue;
			} else {	
				if(compareNo == project_no) {
					continue;
				} else {
					articleList.add(ad);
					compareNo = project_no;
					i++;
				}			
			}
		}	
	}	
%>

<div class="col-sm-2" style="margin-bottom: 50px;">
	<% if(articleList != null && !articleList.isEmpty()) { %>
		<% for(int j=0; j< articleList.size(); j++) { %>
			<%
				ProjectBoardBean bean = articleList.get(j);
				if(bean.getAttachedFile() == null) {
					bean.setAttachedFile("default.jpg");
				}
			%>
			<div class="ads-item" onclick="location.href='${ctxPath}/projectRead.do?project_no=<%=bean.getProjectNo()%>'">
				<img src="${ctxPath}/attachedFile/<%=bean.getAttachedFile()%>" alt="<%=bean.getAttachedFile()%>" width="100%" height="45%"/>
				<div style="padding: 10px;">
					<div style="height: 100px;">
						<p style="font-weight: bold;"><%=bean.getSubject() %></p>
						<p style="margin-top: -5px;"><small><%=bean.getCreator() %></small></p>
					</div>
						
					<div class="progress" style="height: 4px;">
						<%
							long progress = ((long)bean.getNowFund()*100)/bean.getObjFund();
						%>
  						<div class="progress-bar" role="progressbar" 
  							 aria-valuenow="<%=progress%>" 
  							 aria-valuemin="0" aria-valuemax="100" 
  							 style="width:<%=progress %>%; background-color: #4263EB;">
  						</div>
					</div>
					<%
						Date today = new Date();
						long diff = bean.getEndDate().getTime() - today.getTime();
						long diffDays = diff/ (24 * 60 * 60 * 1000);
					%>
					<p style="margin-top: -10px; float: left;">
						<% if(diffDays > 0) { %>
							<small><%=diffDays%>일 남음</small>
						<% } else if(diffDays == 0) { %>
							<small style="font-weight: bold; color: red;">오늘 마감</small>
						<% } else { %>
							<small style="font-weight: bold; color: red;">마감</small>
						<% } %>
					</p>
					<p style="margin-top: -10px; float: right;"><small>진행율: <%=progress%>%</small></p>
				</div>			
			</div>
	<% 
			}	
		}
	%>
</div>