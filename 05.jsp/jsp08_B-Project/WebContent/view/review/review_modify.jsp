<%@page import="com.b_project.model.review.model.ReviewBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}
<%
	ReviewBoardBean article = (ReviewBoardBean)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
%>
<c:set var="category" 
	   value='<%= new String[]{"게임","공연","디자인","만화","미술","사진",
							   "영화/비디오","요리","음악","출판","테크놀로지","패션"} %>'/>
							   
<!DOCTYPE>
<html>
<head>
	<title>리뷰 수정</title>
  	
  	<jsp:include page="/includee/init.jsp"/>
  	
  	<!-- include summernote css/js-->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	
	<style>
  	input:hover, textarea:hover, select:hover {
    	border-color: #FF3533;
    }
	</style>
	
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	<div class="container-fluid main-container">    
      	<div class="col-sm-2"></div>		
      	<div class="col-sm-8 main-content text-left">
        	<form action="review_modify.do" enctype="multipart/form-data" method="post">
        		<input type="hidden" name="review_no" value="<%=article.getReviewNo() %>"/>
        		<input type="hidden" name="page" value="<%=nowPage %>"/>
        		<h3 style="font-weight: bold">리뷰 수정하기</h3>
        		<br />
	        	<table class="table summernote">
	        		<tr>
	        			<td style="width: 15%"><label for="category">카테고리</label></td>
	        			<td style="width: 15%">
	        				<select name="review_category" id="category" class="form-control input-sm">
	        					<c:forEach var="i" items="${category}">
	        						<option value="${i}">${i}</option>
	        					</c:forEach>
 							</select>
	        			</td>
	        			<td></td>
	        		</tr>
	        		<tr>
	        			<td><label for="subject">제목</label></td>
	        			<td colspan="4">
	        				<input type="text" name="review_subject" id="reviewSubject" class="form-control input-sm" placeholder="프로젝트 제목을 입력해주세요" value="<%=article.getReviewSubject() %>" required="required"/>
	        			</td>
	        		</tr>
	        		<tr height="500px">
	        			<td colspan="5">
	        				<textarea name="review_content" id="summernote"><%=article.getReviewContent() %></textarea>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td><label for="reviewFile">대표 이미지</label></td>
	        			<td colspan="3">
	        				<input type="file" name="reviewFile" id="reviewFile"/>
	        			</td>
	        			<td> 
	        				<input type="submit" style="float: right; width: 60px"  value="확 인" />
	        			</td>
	        		</tr>
	        	</table>
        	</form>
		</div>
		<jsp:include page="/includee/ads.jsp"/>
  	</div>
	
	<jsp:include page="/includee/footer.jsp"/>
	
	<script>
		$(document).ready(function() {
     		$('#summernote').summernote({
             	height: 450,                 // set editor height
             	minHeight: null,             // set minimum height of editor
             	maxHeight: null,             // set maximum height of editor
             	focus: true,                 // set focus to editable area after initializing summernote
             	disableResizeEditor: true
     		});
		});
	</script>
</body>
</html>