<%@page import="java.text.DecimalFormat"%>
<%@page import="com.b_project.model.PageInfo"%>
<%@page import="java.util.Date"%>
<%@page import="com.b_project.model.project.model.ProjectBoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	ArrayList<ProjectBoardBean> articleList = (ArrayList<ProjectBoardBean>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();	
	
	String searchOption = (String)request.getAttribute("search-option");
	String searchWord = (String)request.getAttribute("search-word");
	String orderBy = (String)request.getAttribute("orderBy");
	boolean asc = (boolean)request.getAttribute("asc");
	boolean finishedOrNot = (boolean)request.getAttribute("finishedOrNot");
	
	DecimalFormat fmt = new DecimalFormat("###,###");
%>
<!DOCTYPE>
<html>
<head>
	<title></title>
	
	<jsp:include page="/includee/init.jsp"/>
	
	<style>
	#header {
		margin-top: 50px;
		text-align: center;
		font-size: 50px;
		font-weight: bold;
		border: thin solid #D7D7D7;
		height: 150px;
		line-height: 135px;
	}
	</style>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	<div id="header">
		<c:choose>
			<c:when test="${orderBy == 'now_fund'}">
				인기 프로젝트
			</c:when>
			<c:when test="${orderBy == 'DATEDIFF(END_DATE, NOW())'}">
				마감 앞둔 프로젝트
			</c:when>
			<c:when test="${finishedOrNot == 'true'}">
				마감된 프로젝트
			</c:when>
			<c:otherwise>
				모든 프로젝트
			</c:otherwise>
		</c:choose>
	</div>
	<div class="container-fluid" style="background-color: #f0f0f0">
		<div class="container" style="margin-top: 40px"> 
  		
	  	<% if(articleList != null && !articleList.isEmpty()) { %>
		  	<% for(int i=0; i< articleList.size(); i++) { %>
	   			<%
					ProjectBoardBean bean = articleList.get(i);
					if(bean.getAttachedFile() == null) {
						bean.setAttachedFile("default.jpg");
					}
					Date today = new Date();
					long diff = bean.getEndDate().getTime() - today.getTime();
					long diffDays = diff/ (24 * 60 * 60 * 1000);
				%>
				<div class="col-sm-3 items">
					<div class="project-item" 
						 onclick="location.href='${ctxPath}/projectRead.do?project_no=<%=bean.getProjectNo()%>&page=<%=nowPage%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&orderBy=<%=orderBy%>&asc=<%=asc%>&finishedOrNot=<%=finishedOrNot%>'">
						<img src="${ctxPath}/attachedFile/<%=bean.getAttachedFile()%>" alt="<%=bean.getAttachedFile()%>" width="100%" height="53%"/>
						<div style="padding: 15px;">
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
							
							<p style="margin-top: -10px; float: left;">
							<% if(diffDays > 0) { %>
								<small><%=diffDays%>일 남음</small>
							<% } else if(diffDays == 0) { %>
								<small style="font-weight: bold; color: red;">오늘 마감</small>
							<% } else { %>
								<small style="font-weight: bold; color: red;">마감</small>
							<% } %>
							</p>
							<p style="margin-top: -10px; float: right;"><small><%=fmt.format(bean.getNowFund()) %>원(<%=progress%>%)</small></p>
						</div>			
					</div>
				</div>
			<% } %>
			<%
				int mod = articleList.size() % 4;
				if(mod != 0) {
					for(int i=0; i<4-mod; i++) {
			%>
						<div class="col-sm-3 items"><div class="project-item"></div></div>
			<%
					}
				}
			%>
			
			<div style="text-align: center;">
				<% if(nowPage <= 1) { %>
					<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px; font-size: 12px">&lt&lt</button>
					<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px 0px; font-size: 12px">&lt</button>
				<% } else { %>
					<a href="${ctxPath}/projectDisplay.do?page=1&search-option=<%=searchOption%>&search-word=<%=searchWord%>&orderBy=<%=orderBy%>&asc=<%=asc%>&finishedOrNot=<%=finishedOrNot%>" class="btn btn-primary" style="float: left; margin: 20px; font-size: 12px">&lt&lt</a>
					<a href="${ctxPath}/projectDisplay.do?page=<%=nowPage-1%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&orderBy=<%=orderBy%>&asc=<%=asc%>&finishedOrNot=<%=finishedOrNot%>" class="btn btn-primary" style="float: left; margin: 20px 0px; font-size: 12px">&lt</a>
				<% } %>
								
				<% if(nowPage >= maxPage) { %>
					<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px; font-size: 12px">&gt&gt</button>
					<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px 0px; font-size: 12px">&gt</button>
				<% } else { %>
					<a href="${ctxPath}/projectDisplay.do?page=<%=maxPage%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&orderBy=<%=orderBy%>&asc=<%=asc%>&finishedOrNot=<%=finishedOrNot%>" class="btn btn-primary" style="float: right; margin: 20px; font-size: 12px">&gt&gt</a>
					<a href="${ctxPath}/projectDisplay.do?page=<%=nowPage+1%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&orderBy=<%=orderBy%>&asc=<%=asc%>&finishedOrNot=<%=finishedOrNot%>" class="btn btn-primary" style="float: right; margin: 20px 0px; font-size: 12px">&gt</a>
				<% } %>
       								
				<ul class="pagination">
				<%
					for(int i=startPage; i<=endPage; i++) {
						if(i==nowPage) {
				%>
							<li class="active"><a href="#"><%= i %></a></li>
				<%
						} else {
				%>
							<li><a href="${ctxPath}/projectDisplay.do?page=<%= i %>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&orderBy=<%=orderBy%>&asc=<%=asc%>&finishedOrNot=<%=finishedOrNot%>"><%= i %></a></li>
				<%		
						}
					}
				%>
       			</ul> 
			</div>
		<% } %>
			<div style="margin: 0 auto 60px auto; width: 45%;">
				<form action="projectDisplay.do" method="post">
					<select name="search-option" class="form-control" style="width: 23%; float: left;">
						<option value="제목">제목</option>
						<option value="제목과내용">제목+내용</option>
					</select>
					<input type="hidden" name="finishedOrNot" value="<%=finishedOrNot%>"/>
					<input type="text" name="search-word" class="form-control" style="width: 60%; float: left; margin-left: 2%;"/>
					<input type="submit" class="btn btn-default" style="float: right; width: 12%;" value="검색"/>
				</form>
			</div>	
		</div>
  	</div>
 
  	<jsp:include page="/includee/footer.jsp"/>
</body>
</html>