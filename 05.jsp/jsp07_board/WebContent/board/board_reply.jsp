<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<h3>게시글 댓글 쓰기</h3>
			<p>기존 게시글에 댓글을 다는 서비스입니다!!!</p>
		</div>
		
		<form action="boardReply.do" method="post" name="boardForm">
			<input type="hidden" name="p" value="${ param.p }"/>
			<input type="hidden" name="bno" value="${ board.getBno() }"/>	
			<input type="hidden" name="re_ref" value="${ board.getRe_ref() }"/>	
			<input type="hidden" name="re_lev" value="${ board.getRe_lev() }"/>	
			<input type="hidden" name="re_seq" value="${ board.getRe_seq() }"/>	

			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-user"></i></span></div>
				<input type="text" class="form-control" name="writer" id="writer" value="홍길순" required placeholder="게시글작성자를 입력하세요..."/>
			</div>	
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-key"></i></span></div>
				<input type="password" class="form-control" name="pass" id="pass" value="1" required placeholder="게시글 비밀번호를 입력하세요..."/>
			</div>
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-clipboard"></i></span></div>
				<input type="text" class="form-control" name="subject" id="subject" value="댓글제목" required placeholder="게시글 제목을 입력하세요..."/>
			</div>
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-comment-dots"></i></span></div>
				<textarea name="content" id="content" cols="40" rows="15" class="form-control" required placeholder="상세내용을 입력하세요!!!">댓글 상세내용...</textarea>
			</div>	
			
			<div class="form-group input-group">			
				<div class="form-group input-group mt-md-5 justify-content-center">
					<input type="submit" class="btn btn-success float-right login-btn" value="댓글등록"/>
					<input type="reset" class="btn btn-success float-right login-btn ml-sm-2" value="새로고침"/>
				</div>			
			</div>												
		</form>
	</div>
</body>
</html>