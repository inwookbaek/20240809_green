<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	파일전송방식
	
	게시판이나 자료실등에서 제품이미지등 파일을 업로드할 경우 request기본객체등에서 제공하는 기능
	만으로는 업로드 파일을 처리할 수가 없다.
	
	일반파라미터를 전송할 때 사용하는 인코딩과 파일을 업로드할 때 사용하는 인코딩은 다르다.
	파일을 업로드할 경우 스트림기반의 전송방식에는 인코딩방식에 따라 전송하는 데이터형식이 달라진다.
	
	파일을 업로드하기 위해서는 multipart/form-data 인코딩방식을 사용해야 하는데 데이터를 multipart/
	form-data인코딩방식으로 전송하기 위해서는 form태그의 enctype속성값을 "multipart/form-data"로
	지정해 주면 된다. 그리고 "multipart/form-data"인코딩 방식은 POST전송방식의 한 종류이기 때문에
	method속성도 POST로 지정해야 한다.
	
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>FileUpload - multipart/form-data</h1>
	<form action="jsp0602_multipartData.jsp" 
			method="post" 
			enctype="multipart/form-data">
		text1: <input type="text" name="text1" /><br>
		file1: <input type="file" name="file1" /><br>
		file2: <input type="file" name="file2" /><br>
		<input type="submit" value="파일업로드"/>
	</form>
</body>
</html>