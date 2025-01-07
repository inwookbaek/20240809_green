<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Form</h1>
			
	<form action="./jsp0210_viewParameter.jsp" method="post">
		이름: <input type="text" name="name" size="10"/><br>
		나이: <input type="text" name="age" size="10"/><br>
		주소: <input type="text" name="addr" size="30"/><br>
		좋아하는 동물 : 
			<input type="checkbox" name="pet" value="dog"/>강아지		
			<input type="checkbox" name="pet" value="cat"/>고양이
			<input type="checkbox" name="pet" value="pig"/>도야지
			<br />
		<input type="submit" value="전송"/>
	</form>
</body>
</html>