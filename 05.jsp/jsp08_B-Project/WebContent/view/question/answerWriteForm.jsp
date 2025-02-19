<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.b_project.model.question.model.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	Question question = (Question)request.getAttribute("article");
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss ");
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
			<h2 style="font-weight: bold">답변하기</h2>
        	<br />
			<table class="table board-detail">
				<tr>
					<td>제목</td>
					<td colspan="3">: &nbsp;&nbsp;<%=question.getSubject()%></td>
				</tr>
				<tr>
					<td width="10%" style="border-bottom: thin solid #777777;">작성자</td>
					<td width="40%" style="border-bottom: thin solid #777777;">:
						&nbsp;&nbsp;<%=question.getId() %></td>
					<td width="10%" style="border-bottom: thin solid #777777;">수정일</td>
					<td width="40%" style="border-bottom: thin solid #777777;">:
						&nbsp;&nbsp;<%=fmt.format(question.getModDate())%></td>
				</tr>
				<tr>
					<td colspan="4"><br /> <%=question.getContent() %> <br /></td>
				</tr>
			</table>
			<form action="${ctxPath}/answerWrite.do" method="post">
				<input type="hidden" name="q_no" value="${param.q_no}"/>
        		
	        	<table class="table summernote">
	        		<tr>
	        			<td colspan="2">
	        				<textarea name="content" id="summernote"></textarea>
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