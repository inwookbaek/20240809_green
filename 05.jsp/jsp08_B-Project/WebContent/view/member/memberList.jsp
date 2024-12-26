<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.b_project.model.PageInfo"%>
<%@page import="com.b_project.model.member.model.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	ArrayList<Member> memberList = (ArrayList<Member>)request.getAttribute("memberList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();	
	
	String searchOption = (String)request.getAttribute("search-option");
	String searchWord = (String)request.getAttribute("search-word");
%>
<!DOCTYPE>
<html>
<head>
	<title>관리자 모드</title>
  	
  	<jsp:include page="/includee/init.jsp"/>
  	
  	<style>
 	table > tbody > tr {
		padding: 12px 10px;
	}
	
	table > tbody > tr:hover {
		background-color: #F0F0F0;
		cursor: pointer;
		color: #4263EB;
	}
  	</style>
  	
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
  	
  	<div class="container-fluid main-container">    
      	<jsp:include page="/includee/adminAside.jsp"/>
		
      	<div class="col-sm-8 main-content"> 
      		<h2 style="font-weight: bold; text-align: left;">회원 관리</h2>
      		<br />
      		<div style="height: 700px;">
	       		<table class="table list">
	       			<thead>
	   					<tr>
	    					<th>아이디</th>
	    					<th>이름</th>
	    					<th>전화번호</th>
	    					<th>이메일</th>
	    					<th>가입일</th>
	    					<th>등급</th>
	   					</tr>
	       			</thead>
	       			<tbody>
	   					<%
	   						SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
	   						SimpleDateFormat fmt2 = new SimpleDateFormat("a hh:mm");
	   						Date today = new Date();
	   						today.setHours(0);
	   						today.setMinutes(0);
	   						today.setSeconds(0);
	   						for(Member member : memberList) {
	   					%>
	       				<tr onclick="location.href='${ctxPath}/memberDetail.do?id=<%=member.getId() %>'">
	       					<td><%=member.getId() %></td>
	       					<td><%=member.getName() %></td>
	       					<td><%=member.getTel() %></td>
	       					<td><%=member.geteMail() %></td>
	       					<% 
	       						if(today.compareTo(member.getRegDate()) > 0) {
	       					%>
	       						<td><%=fmt1.format(member.getRegDate()) %></td>
	       					<%
	       						} else {
	       					%>
	       						<td><%=fmt2.format(member.getRegDate()) %></td>
	       					<%
	       						}
	       					%>
	       					<td><%=member.getLevel() %></td>
	       				<%
	   						}
	       				%>
	        			</tr>
	       			</tbody>
	    			<tfoot>
	    				<tr>
	   						<td colspan="6">			
								<% if(nowPage <= 1) { %>
									<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px; font-size: 12px">&lt&lt</button>
									<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px 0px; font-size: 12px">&lt</button>
								<% } else { %>
									<a href="${ctxPath}/memberList.do?page=1&search-option=<%=searchOption%>&search-word=<%=searchWord%>" class="btn btn-primary" style="float: left; margin: 20px; font-size: 12px">&lt&lt</a>
									<a href="${ctxPath}/memberList.do?page=<%=nowPage-1%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>" class="btn btn-primary" style="float: left; margin: 20px 0px; font-size: 12px">&lt</a>
								<% } %>
								
								<% if(nowPage >= maxPage) { %>
									<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px; font-size: 12px">&gt&gt</button>
									<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px 0px; font-size: 12px">&gt</button>
								<% } else { %>
									<a href="${ctxPath}/memberList.do?page=<%=maxPage%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>" class="btn btn-primary" style="float: right; margin: 20px; font-size: 12px">&gt&gt</a>
									<a href="${ctxPath}/memberList.do?page=<%=nowPage+1%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>" class="btn btn-primary" style="float: right; margin: 20px 0px; font-size: 12px">&gt</a>
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
	 											<li><a href="${ctxPath}/memberList.do?page=<%= i %>&search-option=<%=searchOption%>&search-word=<%=searchWord%>"><%= i %></a></li>
	       							<%	
	       									}
	       								}
	       							%>
	       						</ul> 
	       					</td>
	       				</tr>
	       				
	       				<tr>
	       					<td colspan="6" style="border: 0">
	       						<div class="col-sm-2"></div>
	       						<div class="col-sm-8">
									<form action="memberList.do" method="post">
										<select name="search-option" class="form-control" style="width: 23%; float: left;">
											<option value="아이디">아이디</option>
											<option value="작성자">작성자</option>
										</select>
										<input type="text" name="search-word" class="form-control" style="width: 60%; float: left; margin-left: 2%;"/>
										<input type="submit" class="btn btn-default" style="float: right; width: 12%;" value="검색"/>
									</form>
								</div>
								<div class="col-sm-2"></div>
	       					</td>
	       				</tr>
	       			</tfoot>  			
	       		</table>
       		</div>
      	</div>
      	<jsp:include page="/includee/ads.jsp"/>
    </div>
  	<jsp:include page="/includee/footer.jsp"/>
</body>
</html>