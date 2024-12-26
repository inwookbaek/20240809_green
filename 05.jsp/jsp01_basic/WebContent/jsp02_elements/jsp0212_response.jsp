<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	response기본객체
	
	response기본객체는 request기본객체와 반대의 기능을 수행한다. request기본객체가
	웹브라우저가 전송한 요청정보를 담고있다면 response기본객체는 웹브라우저에 보내는
	응답정보를 담느다.
	
	response기본객체 응답관련 메서드
	
	1. addDateHeader(String name, long date) : name헤더에 date를 추가
	2. addHeader(String name, String value) : name헤더에 value값을 추가
	3. addIntHeader(String name, int value) : name헤더에 정수값을 추가
	4. setHeader(String name, String value) : name헤더에 value값을 지정
	5. setntHeader(String name, int value) : name헤더에 정수값을 지정
	6. setDateHeader(String name, long date) : name헤더에 date를 지정 
	
	웹브라우저 캐시제어관련 응답헤더
	
	JSP를 비롯한 웹어플리케이션을 개발할 경우 새로운 내용을 DB에 추가했음에도 웹브라우저에
	출력되는 내용이 변경되지 않는 경우가 있는데 이 이유중하나는 웹브라으저가 서버가 생성한
	결과를 출력하지 않고 캐시에 저장된 데이터를 출력하기 때문이다.
	
	캐시(Cache)란? 웹브라우저가 WAS에 JSP실행을 요청하고 잠시뒤에 동일한 JSP실행을 요청한
	경우 첫번째요청과 두번째요청사이에 결과차이가 없을 경우에 불필요한 응답결과를 반복해서
	요청한 셈이 된다. 캐시는 이렇게 동일한 데이터를 중복해서 로딩하지 않도록 할 경우에
	사용된다. 
	웹브라우저는 첫번째요청시 응답결과를 로컬PC의 임시저장장소인 캐시에 저장한다. 이후
	동일한 URL에 대한 요청이 있을 경우에 WAS에 접근하지 않고 로컬PC에 저장된 응답결과를
	사용한다. 캐시에 보관된 데이터를 사용할 경우 서버에 접근하지 않기 때문에 훨씬 빠른
	결과를 웹브라우저에 출력할 수 있게 된다.
	따라서 변경이 발생하지 않는 JSP의 응답결과나 이미지, 정적인 HTML등은 캐시에 보관함
	으로써 웹브라우저의 응답속도를 향상시킬 수 있게 한다.
	
	response기본객체가 제공하는 헤더추가 메서드
	1. Cache-Control : 이 헤더값을 "no-cache"로 지정하면 웹브라우저는 응답결과를 캐시안함
	                   (HTTP1.1버전)
	2. Pragma : 이 헤더값을 "no-cache"로 지정하면 웹브라우저는 응답결과를 캐시안함
	            (HTTP1.0버전)
	3. Expires : 응답결과의 만료일을 지정 
		
	리다이렉트?
	
	response기본객체에서 가장 많이 사용되는 기능중 하나이다. 리다이렉트는 웹서버가 웹브
	라우저에게 다른 페이지로 이동하라고 응답하는 기능이다.
	response기본객체는 sendRedirect(String location)메서드를 이용해서 웹브라우저가 리다
	이렉트하도록 지시할 수 있다.
	
-->
<%
	// http://localhost:8080/jsp01_basic/jsp02_elements/jsp0212_response.jsp?id=hong
	String id = request.getParameter("id");
	if(id != null && id.equals("hong")) {
		response.sendRedirect("jsp0209_form.jsp");
	} else {
%>
		<!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<title></title>
		</head>
		<body>
			<h1 style="color: red;">요청된 ID가 없거나 id가 'hong'이 아닙니다!!</h1>
		</body>
		</html>		
<%
	}
%>