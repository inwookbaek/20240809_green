<%@page import="com.b_project.model.project.service.ProjectReadService"%>
<%@page import="com.b_project.model.project.model.ProjectBoardBean"%>
<%@page import="java.util.Date"%>
<%@page import="com.b_project.model.PageInfo"%>
<%@page import="com.b_project.model.funding.model.FundingBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.b_project.model.member.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	ArrayList<FundingBean> fundingList = (ArrayList<FundingBean>)request.getAttribute("fundingList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();	
%>

<!DOCTYPE>
<html>
<head>
	<title></title>
	
	<jsp:include page="/includee/init.jsp"/>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	
	<div class="container-fluid main-container">
		<c:if test="${authUser.id != 'admin'}">
			<jsp:include page="/includee/myPageAside.jsp"/>
		</c:if>
		<c:if test="${authUser.id == 'admin'}">
			<jsp:include page="/includee/adminAside.jsp"/>
		</c:if>
		
		
		<div class="col-sm-8 main-content"> 
			<h2 style="font-weight: bold; text-align: left;">
				내 후원 목록
			</h2>
			<br />
      		<div style="height: 700px;">
				<table class="table list">
					<thead>
						<tr>
							<th>번호</th>
							<th>프로젝트 제목</th>
							<th>후원 날짜</th>
							<th>후원 금액</th>
							<th>남은 기간</th>
							<th>모금률</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty fundingList}">
							<%
								SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
		   						SimpleDateFormat fmt2 = new SimpleDateFormat("a hh:mm");
		   						Date today = new Date();
		   						today.setHours(0);
		   						today.setMinutes(0);
		   						today.setSeconds(0);
		   						
		   						ProjectReadService projectReadService = new ProjectReadService();
		   						
								for(FundingBean fundingBean : fundingList) {								
									ProjectBoardBean projectBoardBean = projectReadService.getArticle(fundingBean.getProjectNo());
									
									long diff = projectBoardBean.getEndDate().getTime() - today.getTime();
									long diffDays = diff/ (24 * 60 * 60 * 1000);
									long progress = ((long)projectBoardBean.getNowFund()*100)/projectBoardBean.getObjFund();
							%>
							<tr>
								<td><%=fundingBean.getFundNo() %></td>
								<td style="text-align: left;">
									<a href="${ctxPath}/projectRead.do?project_no=<%=projectBoardBean.getProjectNo()%>"><%=projectBoardBean.getSubject()%></a>
								</td>
								<% 
		       						if(today.compareTo(fundingBean.getFundDate()) > 0) {
		       					%>
									<td><%=fmt1.format(fundingBean.getFundDate()) %></td>
								<%
		       						} else {
		       					%>
		       						<td><%=fmt2.format(fundingBean.getFundDate()) %></td>
		       					<%
		       						}
		       					%>
		       					<td><%=fundingBean.getMoney() %> 원</td>
								<td><%=diffDays %> 일</td>
								<td><%=progress %> %</td>
							</tr>
							<%
								}
							%>
						</c:if>
						<c:if test="${empty fundingList}">
							<tr>
								<td colspan="6"><br />후원하신 이력이 없습니다.</td>
							</tr>
		       			</c:if>
					</tbody>
					
					<tfoot>
						<c:if test="${!empty fundingList}">
		    				<tr>
		   						<td colspan="6">			
									<% if(nowPage <= 1) { %>
										<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px; font-size: 12px">&lt&lt</button>
										<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px 0px; font-size: 12px">&lt</button>
									<% } else { %>
										<a href="${ctxPath}/fundingList.do?page=1&id=${param.id}" class="btn btn-primary" style="float: left; margin: 20px; font-size: 12px">&lt&lt</a>
										<a href="${ctxPath}/fundingList.do?page=<%=nowPage-1%>&id=${param.id}" class="btn btn-primary" style="float: left; margin: 20px 0px; font-size: 12px">&lt</a>
									<% } %>
									
									<% if(nowPage >= maxPage) { %>
										<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px; font-size: 12px">&gt&gt</button>
										<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px 0px; font-size: 12px">&gt</button>
									<% } else { %>
										<a href="${ctxPath}/fundingList.do?page=<%=maxPage%>&id=${param.id}" class="btn btn-primary" style="float: right; margin: 20px; font-size: 12px">&gt&gt</a>
										<a href="${ctxPath}/fundingList.do?page=<%=nowPage+1%>&id=${param.id}" class="btn btn-primary" style="float: right; margin: 20px 0px; font-size: 12px">&gt</a>
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
		 											<li><a href="${ctxPath}/fundingList.do?page=<%= i %>&id=${param.id}"><%= i %></a></li>
		       							<%		
		       									}
		       								}
		       							%>
		       						</ul> 
		       					</td>
		       				</tr>
		       			</c:if>
					</tfoot>
				</table>
			</div>
		</div>
		
		<jsp:include page="/includee/ads.jsp"/>
	</div>
	
	<jsp:include page="/includee/footer.jsp"/>
</body>
</html>