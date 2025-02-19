<%@page import="java.util.Date"%>
<%@page import="com.b_project.model.project.model.ProjectBoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.b_project.model.project.service.ProjectListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
//	메인페이지에 3개의 다른 기준으로 정렬된 프로젝트 아이템을 4개씩 읽어와 나타낸다.
	int limit = 4;
	ProjectListService projectListService = new ProjectListService();
	
	ArrayList<ProjectBoardBean> hotArticleList = projectListService.getArticleList(1, limit, "now_fund", false);
	ArrayList<ProjectBoardBean> newArticleList = projectListService.getArticleList(1, limit, "mod_date", false);
	ArrayList<ProjectBoardBean> oldArticleList = projectListService.getArticleList(1, limit, "DATEDIFF(END_DATE, NOW())", true);

//	현재 데이터베이스에 저장된 프로젝트의 갯수가 4개가 안 될 경우, 저장된 갯수만큼만 화면에 표현한다.
	if(oldArticleList.size()<4) {
		limit = oldArticleList.size();
	}

//	금액 표시 포맷
	DecimalFormat fmt = new DecimalFormat("###,###");
%>
<!DOCTYPE html>
<html>
<head>
	<title>B-Project</title>
	
  	<jsp:include page="/includee/init.jsp"/>
  	
  	<style>	
   	.carousel-inner img {
        width: 100%;
        margin: auto;
        min-height:200px;
    }

    /* Hide the carousel text when the screen is less than 600 pixels wide */
    @media (max-width: 600px) {
      	.carousel-caption {
        	display: none; 
      	}
    }
  	</style>
</head>
<body>
  	<jsp:include page="/includee/navbar.jsp"/>
  	
  	<div id="myCarousel" class="carousel slide" data-ride="carousel">
      	<!-- Indicators -->
      	<ol class="carousel-indicators">
        	<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        	<li data-target="#myCarousel" data-slide-to="1"></li>
        	<li data-target="#myCarousel" data-slide-to="2"></li>
      	</ol>

      	<!-- Wrapper for slides -->
      	<div class="carousel-inner" role="listbox">
        	<div class="item active">
          		<img src="${ctxPath}/img/a1.jpg" alt="Image">
        		<div class="carousel-caption">
           			<h3><strong> B-PROJECT </strong></h3>
           			<p><strong> Become a Business Booster </strong></p>
        		</div>      
        	</div>

        	<div class="item">
          		<img src="${ctxPath}/img/a2.jpg" alt="Image">
          		<div class="carousel-caption">
            		<h3><strong> B-PROJECT </strong></h3>
            		<p><strong> Become a Business Booster </strong></p>
          		</div>      
        	</div>
        	
        	<div class="item">
          		<img src="${ctxPath}/img/a3.jpg" alt="Image">
          		<div class="carousel-caption">
            		<h3><strong> B-PROJECT </strong></h3>
            		<p><strong> Become a Business Booster </strong></p>
          		</div>      
        	</div>
      	</div>

      	<!-- Left and right controls -->
      	<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        	<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        	<span class="sr-only">Previous</span>
      	</a>
      	<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        	<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        	<span class="sr-only">Next</span>
      	</a>
  	</div>
  	
  	<div class="container-fluid" style="background-color: #f0f0f0">
	  	
	  	<div class="container" style="margin-top: 30px">  		
	  	<% if(hotArticleList != null && !hotArticleList.isEmpty()) { %>	
		  	<!-- 인기프로젝트 -->
		  	<br />
		  	<h5 style="font-weight: bold; font-size: 20px;">인기 프로젝트</h5>
		  	<br />
		  	<% for(int i=0; i< limit; i++) { %>
	   		<div class="col-sm-3 items">
   			<%
				ProjectBoardBean bean = hotArticleList.get(i);
   			//	첨부된 이미지 파일이 없을 경우 default이미지로 대체한다
				if(bean.getAttachedFile() == null) {
					bean.setAttachedFile("default.jpg");
				}
			%>
				<div class="project-item" onclick="location.href='${ctxPath}/projectRead.do?project_no=<%=bean.getProjectNo()%>'">
					<img src="${ctxPath}/attachedFile/<%=bean.getAttachedFile()%>" alt="<%=bean.getAttachedFile()%>" width="100%" height="53%"/>
					<div style="padding: 15px;">
						<div style="height: 100px;">
							<p style="font-weight: bold;"><%=bean.getSubject() %></p>
							<p style="margin-top: -5px;"><small><%=bean.getCreator() %></small></p>
						</div>
						
						<div class="progress" style="height: 4px;">
						<%
						//	모금률
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
						//	프로젝트의 남은 기간(일)
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
						<p style="margin-top: -10px; float: right;"><small><%=fmt.format(bean.getNowFund())%>원(<%=progress%>%)</small></p>
					</div>			
				</div>
			</div>
			<% } %>
			
			<!-- 새로운 프로젝트 -->
			<h5 style="font-weight: bold; font-size: 20px;">새로운 프로젝트</h5>
		  	<br />
		  	<% for(int i=0; i< limit; i++) { %>
	   		<div class="col-sm-3 items">
   			<%
				ProjectBoardBean bean = newArticleList.get(i);
			//	첨부된 이미지 파일이 없을 경우 default이미지로 대체한다
				if(bean.getAttachedFile() == null) {
					bean.setAttachedFile("default.jpg");
				}
			%>
				<div class="project-item" onclick="location.href='${ctxPath}/projectRead.do?project_no=<%=bean.getProjectNo()%>'">
					<img src="${ctxPath}/attachedFile/<%=bean.getAttachedFile()%>" alt="<%=bean.getAttachedFile()%>" width="100%" height="53%"/>
					<div style="padding: 15px;">
						<div style="height: 100px;">
							<p style="font-weight: bold;"><%=bean.getSubject() %></p>
							<p style="margin-top: -5px;"><small><%=bean.getCreator() %></small></p>
						</div>
						
						<div class="progress" style="height: 4px;">
						<%
						//	모금률
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
						//	프로젝트의 남은 기간(일)
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
						<p style="margin-top: -10px; float: right;"><small><%=fmt.format(bean.getNowFund())%>원(<%=progress%>%)</small></p>
					</div>			
				</div>
			</div>
			<% } %>
			
			<!-- 마감 앞둔 프로젝트 -->
			<h5 style="font-weight: bold; font-size: 20px;">마감 앞둔 프로젝트</h5>
		  	<br />
		  	<% for(int i=0; i< limit; i++) { %>
	   		<div class="col-sm-3 items">
   			<%
				ProjectBoardBean bean =	oldArticleList.get(i);
			//	첨부된 이미지 파일이 없을 경우 default이미지로 대체한다
				if(bean.getAttachedFile() == null) {
					bean.setAttachedFile("default.jpg");
				}
			%>
				<div class="project-item" onclick="location.href='${ctxPath}/projectRead.do?project_no=<%=bean.getProjectNo()%>'">
					<img src="${ctxPath}/attachedFile/<%=bean.getAttachedFile()%>" alt="<%=bean.getAttachedFile()%>" width="100%" height="53%"/>
					<div style="padding: 15px;">
						<div style="height: 100px;">
							<p style="font-weight: bold;"><%=bean.getSubject() %></p>
							<p style="margin-top: -5px;"><small><%=bean.getCreator() %></small></p>
						</div>
						
						<div class="progress" style="height: 4px;">
							<%
							//	모금률
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
						//	프로젝트의 남은 기간(일)
							long diffDays = diff / (24 * 60 * 60 * 1000);
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
						<p style="margin-top: -10px; float: right;"><small><%=fmt.format(bean.getNowFund())%>원(<%=progress%>%)</small></p>
					</div>			
				</div>
			</div>
			<% } %>
		<% } %>	
		</div>
	</div>
	
  	<jsp:include page="/includee/footer.jsp"/>
</body>
</html>
