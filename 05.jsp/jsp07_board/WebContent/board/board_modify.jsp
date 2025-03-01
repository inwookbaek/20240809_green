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
	<%-- ${ board } --%>
	<div class="container mt-sm-5" align="center">
		<div class="jumbotron">
			<h3>게시글수정하기</h3>
			<p>본인이 작성한 게시글을 수정하는 서비스입니다!!!</p>
		</div>
		
		<form action="boardModify.do" method="post" 
				name="boardForm" enctype="multipart/form-data">
			<input type="hidden" name="p" value="${ param.p }"/>
			<input type="hidden" name="bno" value="${ board.getBno() }"/>
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-user"></i></span></div>
				<input type="text" class="form-control" name="writer" id="writer" value="${ board.getWriter() }" readonly/>
			</div>
				
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-key"></i></span></div>
				<input type="password" class="form-control" name="pass" id="pass" value="" required placeholder="게시글 비밀번호를 입력하세요!!!"/>
			</div>
								
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-clipboard"></i></span></div>
				<input type="text" class="form-control" name="subject" id="subject" value="${ board.getSubject() }"/>
			</div>
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-comment-dots"></i></span></div>
				<textarea name="content" id="content" cols="40" rows="15" class="form-control" >${ board.getContent() }</textarea>
			</div>	
			
			<div class="form-group input-group">
				<c:choose>
					<c:when test="${ !empty board.getFile() }">
						<c:set var="choose_file" value="${ board.getFile() }"/>
					</c:when>
					<c:otherwise>
						<c:set var="choose_file" value="업로드할 파일을 선택하세요!!"/>
					</c:otherwise>
				</c:choose>
				<div class="form-group input-group">
					<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-file-alt"></i></span></div>
					<div class="custom-file">
						<label for="file" class="custom-file-label" style="text-align: left;">${ choose_file }</label>
						<input type="file" class="custom-file-input" id="file" name="file"/>
					</div>
				</div>
				
				<div class="form-group input-group mt-md-5 justify-content-center">
					<input type="submit" class="btn btn-success float-right login-btn" value="게시글수정"/>
					<input type="reset" class="btn btn-success float-right login-btn ml-sm-2" value="새로고침"/>
					<a href="javascript:history.go(-1)" class="btn btn-success ml-sm-2 float-right">이전</a>
				</div>				
			</div>		
		</form>
	</div>
		<script>
		$(".custom-file-input").on('change', function() {
			let fileName = $(this).val().split('\\').pop(); // 파일명만선택
			// alert(this.value + "\n" + fileName);
			$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
		})
	</script>
</body>
</html>