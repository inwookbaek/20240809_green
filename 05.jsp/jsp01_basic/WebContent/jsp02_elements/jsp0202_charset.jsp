<%@page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!-- 현재 JSP파일의 캐릭터셋은 utf-8로 설정되어 있지만
	 응답컨텐츠의 캐릭터셋은 charset=iso-8859-1로 설정되어 있기
	 때문에 웹브라우저에서는 글씨가 깨져서 출력이 된다.
	 이런에러를 방지하기 위해서는 캐릭터셋을 일치시켜야 한다.
-->
현재시간 : <%= new java.util.Date() %>
</body>
</html>