<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<center>
		<form action="jsp060402_fileUpload.jsp" method="post" enctype="multipart/form-data">
			<table border="1">
				<tr>
					<td colspan="2" align="center"><h3>파일업로드하기</h3></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="name" value="홍길동"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" value="파일업로드하기..."></td>
				</tr>
				<tr>
					<td>파일명1</td>
					<td><input type="file" name="fileName1"></td>
				</tr>
				<tr>
					<td>파일명2</td>
					<td><input type="file" name="fileName2"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="파일업로드"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>