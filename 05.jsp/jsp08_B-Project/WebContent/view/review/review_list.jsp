<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.b_project.model.review.model.ReviewPageInfo"%>
<%@page import="com.b_project.model.review.model.ReviewBoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	ArrayList<ReviewBoardBean> articleList = (ArrayList<ReviewBoardBean>)request.getAttribute("articleList");
	ReviewPageInfo pageInfo = (ReviewPageInfo)request.getAttribute("pageInfo");
	
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();	
%>

<!DOCTYPE>
<html>
<head>
	<title>리뷰</title>
  	<jsp:include page="/includee/init.jsp"/>		
</head>
<style>
body{
background-color: #f0f0f0
}
.content {
  overflow: hidden; 
  text-overflow: ellipsis;
  white-space: nowrap; 
  width: 120px;
  height:120px;
  display: black;
}

#output p {
	overflow: hidden;
}

</style>
<body>

	<div class="container">
	<jsp:include page="/includee/navbar.jsp"/>
	<table style="  width: 100%;  align:center;">
		<c:if test="${!emptyarticleList}">
		<div style="margin-top:15%;">
		<div>
			<h2 style="font-weight: bold;">프로젝트 펀딩 후기</h2>
			<td style=" margin:auto;" >
	      			<a href="${ctxPath}/review_write.do" class="btn btn-default" style="float: right; margin: 0px 20px; margin-top: -10px;"><span class="glyphicon glyphicon-pencil" style="font-weight: bold"> 쓰기</span></a>
	       	</td>
		</div>	
		</div>
		<%
   			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss ");
			
   			for(ReviewBoardBean bean : articleList) {
   				
   		%>
   		<%			
			if(bean.getReviewFile() == null) {
				bean.setReviewFile("default.jpg");
			}
		%>
		<table style="  margin-top:35px; height: 260px; width: 1128px; margin: auto; background-color: #FFFFFF" >
			<tr onclick="location.href='${ctxPath}/review.do?review_no=<%=bean.getReviewNo()%>&page=<%=nowPage%>'" style="cursor:pointer; ">
				<td rowspan="2"  width= "423px" height="250px">
					<img src="${ctxPath}/reviewFile/<%=bean.getReviewFile()%>" alt="<%=bean.getReviewFile()%>" width = 300px; height = 260px;/>
				</td>
				<td colspan="2" width = "423px" height="35px" style="padding: 20px;">
					<h3><a href="review.do?review_no=<%=bean.getReviewNo()%>&page=<%=nowPage%>">
					<% if(bean.getReviewSubject().length() >= 30) { %>
					<%= bean.getReviewSubject().substring(0, 27) + "..." %>   
					<% } else { %>
					<%= bean.getReviewSubject() %>
					<% } %>
					</a></h3>
				</td>
				<td width = "170px" height="35px">
					리뷰No.<%=bean.getReviewNo() %>
				</td>			
			</tr>			
			<tr style="height:35px; text-align: center; margin-bottom:20px;">			
				<td width = "20%">작성일 : <%=bean.getReviewDate() %></td>
				<td width = "40%">작성자 : <%=bean.getReviewId() %></td>
				<td width = "20%">조회수 : <%=bean.getReviewReadCount() %></td>	
			</tr>
			<hr />
		</table>
			<%
       			}
	        %>
		</c:if>
	</table>	
	<hr />
	<table style=" margin:auto; text-algin: center;">
		<c:if test="${empty articleList}">
			<tr>
				<td>
					<h2>등록된 리뷰가 없습니다.</h2>
					<input type="reset" value="이전페이지로" onClick="history.back()">
				</td>		
			</tr>   			
      	</c:if>
		
	</table>
						
	<br />
			
	<c:if test="${!empty articleList}">
	<table style=" width: 100%; margin-top: 15%; margin-bottom: 15%; align:center; text-align:center;">	
		<tr>
			<td>
				<% if(nowPage <= 1) { %>
					<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px; font-size: 12px" >&lt;&lt;</button>
					<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px 0px; font-size: 12px" >&lt;</button>
				<% } else { %>
					<a href="${ctxPath}/review_list.do?page=1" class="btn btn-primary" style="float: left; margin: 20px; font-size: 12px">&lt;&lt;</a>
				<a href="${ctxPath}/review_list.do?page=<%=nowPage-1%>" class="btn btn-primary" style="float: left; margin: 20px 0px; font-size: 12px">&lt;</a>
				<% } %>
					
				<% if(nowPage >= maxPage) { %>
					<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px; font-size: 12px">&gt;&gt;</button>
					<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px 0px; font-size: 12px">&gt;</button>
				<% } else { %>
					<a href="${ctxPath}/review_list.do?page=<%=maxPage%>" class="btn btn-primary" style="float: right; margin: 20px; font-size: 12px">&gt;&gt;</a>
					<a href="${ctxPath}/review_list.do?page=<%=nowPage+1%>" class="btn btn-primary" style="float: right; margin: 20px 0px; font-size: 12px">&gt;</a>
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
 							<li><a href="${ctxPath}/review_list.do?page=<%= i %>"><%= i %></a></li>
      				<%		
      						}
      					}
      				%>
     			</ul> 	
			</td>
		</tr> 
		<tr>		
	    	<td colspan="5" style="border: 0">
	    		<div class="col-sm-2"></div>
	    		<div class="col-sm-8">
					<form action="review_list.do" method="post">
						<select name="search-option" class="form-control" style="width: 23%; float: left;">
							<option value="제목">제목</option>
							<option value="제목과내용">제목+내용</option>
							
						</select>
						<input type="text" name="search-word" class="form-control" style="width: 60%; float: left; margin-left: 2%;"/>
						<input type="submit" class="btn btn-default" style="float: right; width: 12%;" value="검색"/>
					</form>
				</div>
				<div class="col-sm-2">
					<a href="${ctxPath}/review_write.do" class="btn btn-default" style="float: right;">
						<span class="glyphicon glyphicon-pencil"></span> 쓰기
					</a>
				</div>	
	       	</td>
	       				
       		<td style="border: 0; margin:auto;">
       			
       		</td>
       	</tr>
	</table>
	</c:if>
	</div>
		
	
</body>
</html>