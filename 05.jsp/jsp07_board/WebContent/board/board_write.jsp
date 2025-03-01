<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="./css/button_style.css" />
<title>게시글등록</title>
</head>
<body>
	
	<div class="container" align="center">
		<div class="jumbotron">
			<h3>게시판글쓰기</h3>
			<p>게시판글쓰기 페이지 입니다!! 글을 작성해 주세요!!</p>
		</div>	
		
		<form action="boardWrite.do" method="post" 
			name="boardForm" enctype="multipart/form-data">
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-user"></i></span></div>
				<input type="text" class="form-control" name="writer" id="writer" value="홍길동" required placeholder="게시글작성자를 입력하세요..."/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-key"></i></span></div>
				<input type="password" class="form-control" name="pass" id="pass" value="12345" required placeholder="게시글 비밀번호를 입력하세요..."/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-clipboard"></i></span></div>
				<input type="text" class="form-control" name="subject" id="subject" value="게시글제목" required placeholder="게시글 제목을 입력하세요..."/>
			</div>
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-comment-dots"></i></span></div>
				<textarea name="content" id="content" cols="40" rows="15" class="form-control" required placeholder="상세내용을 입력하세요!!!">상세글내용...</textarea>
			</div>
			
			<div class="form-group input-group">
				<div class="form-group input-group">
					<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-file-alt"></i></span></div>
					<div class="custom-file">
						<label for="file" class="custom-file-label" style="text-align: left;">업로드할 파일을 선택하세요!!</label>
						<input type="file" class="custom-file-input" id="file" name="file"/>
					</div>	
				</div>
				
				<div class="form-group input-group mt-md-5 justify-content-center">
					<input type="submit" class="btn btn-success float-right login-btn" value="게시글등록"/>
					<input type="reset" class="btn btn-success float-right login-btn ml-sm-2" value="새로고침"/>
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










