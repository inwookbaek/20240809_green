<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">  
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./css/button_style.css" />
<title></title>
</head>
<body>
	<%-- ${ board }  --%>
	<div class="container mt-sm-5" align="center">
		<div class="jumbotron">
			<h3>게시글상세조회</h3>
		</div>
		
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-user"></i></span></div>
			<input type="text" class="form-control" name="writer" id="name" value="${ board.getWriter() }" readonly/>
		</div>	
			
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-clipboard"></i></span></div>
			<input type="text" class="form-control" name="subject" id="subject" value="${ board.getSubject() }" readonly/>
		</div>		
		
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-comment-dots"></i></span></div>
			<textarea name="content" id="content" cols="40" rows="15" class="form-control" readonly>${ board.getContent() }</textarea>
		</div>		
		
		<c:if test="${ board.getFile() != ''  or board.getFile() != null}">
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-file-alt"></i></span></div>
				<input type="text" class="form-control" id="file" name="file" value="${ board.getFile() }" readonly/>
			</div>
		</c:if>
		<br />
		
		<div class="form-group input-group">
	
			<a href="boardModifyForm.do?p=${ param.p }&bno=${ board.getBno() }" class="btn btn-success mr-sm-3 login-btn">수정</a>
			<a href="boardDeleteForm.do?p=${ param.p }&bno=${ board.getBno() }" class="btn btn-success mr-sm-3 login-btn">삭제</a>
			<a href="boardReplyForm.do?p=${ param.p }&bno=${ board.getBno() }" class="btn btn-success mr-sm-3 login-btn">답변</a>
			<a href="boardList.do?p=${ param.p }" class="btn btn-success mr-sm-3 login-btn">글목록</a>
		</div>
	</div>
</body>
</html>