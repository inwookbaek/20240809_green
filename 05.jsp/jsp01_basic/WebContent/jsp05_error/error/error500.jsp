<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- 응답상태코드 
		200 : 요청을 정상적으로 처리 상태코드
		400 : 요청이 잘못되었을 경우 상태코드
		      ... 403 : 요청금지된 페이지
		      ... 404 : 요청페이지가 없는 경우
		500 : 서버가 에러가 있을 경우 상태코드
		
		http응답코드 : http://goo.gl/D9th8N 확인	
	-->
	<!-- 
		http응답상태코드에 따라 해당 에러페이지로 포워딩할 경우에는
		web.xml에 환경설정을 해야 한다.
		error-page태그에 error-code와 포워딩할 페이지 location을 설정
		해야 한다.
		
		또한, exception타입별로 에러페이지를 정의할 수 있다.
		예를 둘어서 java.lang.NullPointerException이 발생할 경우
		포워딩될 페이지를 설정할 수 있다.
	-->
	<h1 style="color: red;">요청한 페이지를 찾지 못했습니다!!</h1>
	<strong style="color: orange;">정확한 주소를 다시 입력하세요!!!</strong>
</body>
</html>