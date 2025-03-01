<%@page import="com.b_project.model.question.model.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	Question article = (Question)request.getAttribute("article");
%>

<!DOCTYPE>
<html>
<head>
	<title></title>
	
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
		
		<jsp:include page="/includee/questionAside.jsp"/>
		
		<div class="col-sm-8 main-content">
			<form action="${ctxPath}/questionModify.do" method="post">
				<input type="hidden" name="q_no" value="<%=article.getqNo() %>" />
				<input type="hidden" name="cantchange" value="<%=article.isCantChange() %>" />
				
        		<h2 style="font-weight: bold">질문 수정하기</h2>
        		<br />
	        	<table class="table summernote">
	        		<tr>
	        			<td width="10%"><label for="subject">제목</label></td>
	        			<td width="90%">
	        				<input type="text" name="subject" id="subject" class="form-control input-sm" value="<%=article.getSubject() %>" placeholder="제목을 입력해주세요" required="required"/>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2">
	        				<textarea name="content" id="summernote"><%=article.getContent() %></textarea>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2"> 
	        				<input type="submit" class="btn btn-default" style="float: right; width: 60px"  value="확 인" />
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