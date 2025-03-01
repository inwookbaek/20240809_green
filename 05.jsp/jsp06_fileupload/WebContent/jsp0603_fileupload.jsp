<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>File Upload</h1>
	<form action="upload" method="post" enctype="multipart/form-data">
		text1: <input type="text" name="text1" /><br>
		file1: <input type="file" name="file1" /><br>
		file2: <input type="file" name="file2" /><br>
		<input type="submit" value="파일업로드"/>
	</form>
</body>
</html>