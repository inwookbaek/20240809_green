<%@page import="java.util.Date"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.b_project.model.review.model.ReviewBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	ReviewBoardBean boardBean = (ReviewBoardBean)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
	
	if(boardBean.getReviewFile() == null) {
		boardBean.setReviewFile("default.jpg");
	}

	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	
	Date today = new Date();
	
	String id = "";
	
	if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
		id = request.getParameter("id");
	}
	
%>
<!DOCTYPE>
<html>
<head>
	<title>리뷰</title>
	<jsp:include page="/includee/init.jsp"/>
	
	<style>
		h1,h4{
			font-weight: bold;
		}
		
		#category {
			text-align: center;
			border: thin solid #C2C2C2;
			border-radius: 5px;
			font-weight: bold;
			margin: 0 auto;
			padding: 5px;
			width: 100px;
		}
		
		
	</style>
</head>
<body>	
	<jsp:include page="/includee/navbar.jsp"/>
		
	<div class="container-fluid main-container">
		<div class="container" style="margin: 70px auto;">
			<div id="category"><%=boardBean.getReviewCategory() %></div>
			<h2 style="text-align: center;"><%=boardBean.getReviewSubject()%></h2>
			<br />
			<h4 style="text-align: center;">리뷰어 : <%=boardBean.getReviewId() %></h4>
			<br />
			
			<h5 style= "text-align: center;"><%= boardBean.getReviewContent() %></h5>
			
		</div>
		<div style="padding: 30px;  margin-bottom: 50px;">
			<a class="btn btn-default" style="float: right; margin-left: 10px;"   
			   href="${ctxPath}/review_list.do?page=<%=nowPage%>">
			   	<span class="glyphicon glyphicon-list"></span> 
			   	목록
			</a>
			<c:if test="${authUser.id == article.reviewId }">
				<button class="btn btn-primary" style="float: right; margin-left: 10px;"onclick="deleteConfirm();">
					 삭제
				</button>
				<a class="btn btn-primary" style="float: right; margin-left: 10px;" href="${ctxPath}/review_modify.do?review_no=<%=boardBean.getReviewNo()%>&page=<%=nowPage%>">
					수정
				</a>
			</c:if>
		</div>	
	</div>	
			
	<jsp:include page="/includee/footer.jsp"/>
	
	 <script>
  		function deleteConfirm(){
  			if (confirm("정말 삭제하시겠습니까??") == true){    //확인
  				location.href="${ctxPath}/reviewDelete.do?review_no=<%=boardBean.getReviewNo()%>&page=<%=nowPage%>";
  			} else {   //취소
  		    	return;
  			}
  		}
  	</script>	

</body>
</html>