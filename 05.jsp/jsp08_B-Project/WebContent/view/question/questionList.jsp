<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.b_project.model.PageInfo"%>
<%@page import="com.b_project.model.question.model.Question"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	ArrayList<Question> articleList = (ArrayList<Question>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();	
	
	String searchOption = (String)request.getAttribute("search-option");
	String searchWord = (String)request.getAttribute("search-word");
	String faq = (String)request.getAttribute("faq");
	String id = (String)request.getAttribute("id");
%>
<!DOCTYPE>
<html>
<head>
	<title>고객센터</title>
	
	<jsp:include page="/includee/init.jsp"/>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	
	<div class="container-fluid main-container">
		<c:if test="${authUser.id!='admin'}">
			<jsp:include page="/includee/questionAside.jsp"/>
		</c:if>
		<c:if test="${authUser.id=='admin'}">
			<jsp:include page="/includee/adminAside.jsp"/>
		</c:if>
		
		<div class="col-sm-8 main-content"> 
      		<h2 style="font-weight: bold; text-align: left;">
      			<c:choose>
      				<c:when test="${!empty id}">
      					나의 상담 내역
      				</c:when>
      				<c:when test="${faq=='true'}">
      					자주 묻는 질문
      				</c:when>
      				<c:when test="${faq!='true'}">
      					질문과 답변
      				</c:when>	
      			</c:choose>
      		</h2>
      		<br />
      		<div style="height: 90%;">
				<table class="table list">
					<thead>
						<tr>
							<th style="width: 8%;">번호</th>
							<th style="width: 55%;">제목</th>
							<th style="width: 14%;">작성자</th>
							<th style="width: 14%;">작성일</th>
							<th style="width: 9%;">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty articleList}">
							<%
								SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
		   						SimpleDateFormat fmt2 = new SimpleDateFormat("a hh:mm");
		   						Date today = new Date();
		   						today.setHours(0);
		   						today.setMinutes(0);
		   						today.setSeconds(0);
		   						
								for(Question question : articleList) {
							%>
							<tr>
								<td><%=question.getqNo() %></td>
								<td style="text-align: left;">
									<a href="${ctxPath}/questionRead.do?page=<%=nowPage%>&q_no=<%=question.getqNo()%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&faq=<%=faq %>&id=<%=id%>"><%=question.getSubject() %></a>
									<% if(question.isCantChange()) { %>
										<span class="label label-danger">답변 완료!</span>
									<% } %>
								</td>
								<td>
									<% if(question.getId() != null) { %>
										<%=question.getName()%>(<%=question.getId()%>)
									<% } else { %>
										탈퇴 회원
									<% } %>
								</td>
								<% 
		       						if(today.compareTo(question.getWrtDate()) > 0) {
		       					%>
									<td><%=fmt1.format(question.getWrtDate()) %></td>
								<%
		       						} else {
		       					%>
		       						<td><%=fmt2.format(question.getWrtDate()) %></td>
		       					<%
		       						}
		       					%>
								<td><%=question.getReadCNT() %></td>
							</tr>
							<%
								}
							%>
						</c:if>
						<c:if test="${empty articleList}">
							<tr>
								<td colspan="5"><br />등록된 글이 없습니다</td>
							</tr>
		       			</c:if>
					</tbody>
					
					<tfoot>
						<c:if test="${!empty articleList}">
		    				<tr>
		   						<td colspan="5">			
									<% if(nowPage <= 1) { %>
										<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px; font-size: 12px">&lt&lt</button>
										<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px 0px; font-size: 12px">&lt</button>
									<% } else { %>
										<a href="${ctxPath}/questionList.do?page=1&search-option=<%=searchOption%>&search-word=<%=searchWord%>&faq=<%=faq %>&id=<%=id%>" class="btn btn-primary" style="float: left; margin: 20px; font-size: 12px">&lt&lt</a>
										<a href="${ctxPath}/questionList.do?page=<%=nowPage-1%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&faq=<%=faq %>&id=<%=id%>" class="btn btn-primary" style="float: left; margin: 20px 0px; font-size: 12px">&lt</a>
									<% } %>
									
									<% if(nowPage >= maxPage) { %>
										<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px; font-size: 12px">&gt&gt</button>
										<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px 0px; font-size: 12px">&gt</button>
									<% } else { %>
										<a href="${ctxPath}/questionList.do?page=<%=maxPage%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&faq=<%=faq %>&id=<%=id%>" class="btn btn-primary" style="float: right; margin: 20px; font-size: 12px">&gt&gt</a>
										<a href="${ctxPath}/questionList.do?page=<%=nowPage+1%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&faq=<%=faq %>&id=<%=id%>" class="btn btn-primary" style="float: right; margin: 20px 0px; font-size: 12px">&gt</a>
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
		 											<li><a href="${ctxPath}/questionList.do?page=<%= i %>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&faq=<%=faq %>&id=<%=id%>"><%= i %></a></li>
		       							<%		
		       									}
		       								}
		       							%>
		       						</ul> 
		       					</td>
		       				</tr>
		       			</c:if>
		       			
		       			<tr>
	       					<td colspan="5" style="border: 0">
	       						<div class="col-sm-2"></div>
	       						<div class="col-sm-8">
									<form action="questionList.do" method="post">
										<select name="search-option" class="form-control" style="width: 23%; float: left;">
											<option value="제목">제목</option>
											<option value="제목과내용">제목+내용</option>
										</select>
										<input type="text" name="search-word" class="form-control" style="width: 60%; float: left; margin-left: 2%;"/>
										<input type="submit" class="btn btn-default" style="float: right; width: 12%;" value="검색"/>
										<input type="hidden" name="faq" value="<%=faq%>" />
										<input type="hidden" name="id" value="<%=id%>" />
									</form>
								</div>
								<div class="col-sm-2">
									<a href="${ctxPath}/questionWrite.do" class="btn btn-default" style="float: right;">
										<span class="glyphicon glyphicon-pencil"></span> 쓰기
									</a>
								</div>	
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