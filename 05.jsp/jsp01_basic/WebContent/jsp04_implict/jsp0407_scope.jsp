<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	application > session > page > request
	
	JSP기본객체와 영역(Scope)
	
	웹애플리케이션은 네가지 영역(scope)로 구분된다.
	
	1. page scope : 하나의 JSP페이지를 처리할 때 사용되는 영역

	   -> page영역은 한번의 클라이언트요청에 대해서 하나의 JSP페이지를 영역으로 한다.
	      웹브라우저의 요청이 들어오면 JSP페이지를 실행하는 데 이때 JSP페이지를 실행
	      하는 범위가 하나의 page영역이다.	   
	   
	2. request scope : 하나의 http요청을 처리할 때 사용되는 영역

	   -> request영역은 웹브라우저의 한번의 요청과 관련된다. 웹브라우저의 주소에 URL을
	      입력하거나 링크를 클릭해서 페이지를 이동할 때, 웹브라우저가 웹서버에 전송하는
	      요청이 하나의 request영역이 된다. 웹브라우저가 요청을 보낼 때마다 새로운 request
	      영역이 생성된다. page영역은 오직 하나의 JSP페이지만 을 포함하는데 반해, request
	      영역은 하나의 요청을 처리하는 데 사용되는 모든 JSP페이지를 포함한다.
	
	3. session scope : 하나의 웹브라우저와 관련된 영역
	
	   -> session영역은 하나의 웹브라우저와 관련된 영역이다. 세션이 생성되면 하나의 웹
	      브라우저와 관련된 모든 요청은 하나의 session영역에 포함된다.	   
	      
	4. application scope : 하나의 웹 애플리케이션과 관련된 영역
	
	   -> application영역은 하나의 웹애플리케이션과 관련된 전체영역을 포함한다. 
	
	 
    JSP기본객체의 속성(attribute)사용하기
    
    네개의 기본객체는 각각의 속성을 갖는데 각 기본객체가 존재하는 동안 기본객체의 속성을
    사용할 수 있다. JSP기본객체의 속성은 JSP페이지 사이에서 정보를 주고받거나 공유하기 
    위한 목적으로 사용된다.
    
    속성은<속성이름, 값>의 형태를 갖는데 네개의 기본객체는 서로다른 이름을 갖는 속성을 
    여러개 포함할 수 있다.
    
    JSP기본객체 속성처리 메서드
    
    1. setAttribute(String name, Object value) : 속성값을 지정
    2. getAttribute(String name) : 속성값을 리턴 속성이 없을때는 null을 리턴
    3. removeAttribute(String name) : 속성을 삭제
    4. getAttributeNames() : 속성이름목록을 Enumeration을 리턴(pageContext는 제공하지 않음)

-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>JSP기본객체의 속성처리하기</h1>
	<h3>request의 속성처리</h3>
	<%
		// http://localhost:8088/jsp01_basic/jsp04_implict/jsp0407_scope.jsp?name=홍길동&age=1000
		// get방식은 한글은 안깨지지만 post방식일 경우 깨지기때문에 인코딩처리를 해야 한다.
		request.setCharacterEncoding("utf-8");  
		String name = request.getParameter("name");
		String age = request.getParameter("age");
	%>
	회원이름 : <%= name %><br>
	회원나이 : <%= age %><br>
	<hr />
	
	<h3>application의 속성처리</h3>
	<%
		application.setAttribute("xxx", name);
		application.setAttribute("yyy", request.getParameter("age"));
	%>
	회원이름 : <%= application.getAttribute("xxx") %><br>
	회원나이 : <%= application.getAttribute("yyy") %><br>
	<hr />
	
	<%
		if(name != null && age != null) {
			application.setAttribute("id", name);
			application.setAttribute("pw", age);
			out.println(String.format("회원의 아이디 %s <br>", application.getAttribute("id")));
			out.println(String.format("회원의 비밀번호 %s <br>", application.getAttribute("pw")));
		} else {
	%>
			<h3 style="color: red;">application의 기본객체의 속성은 설정되지 않았습니다!!</h3>
	<%
		}
	%>
</body>
</html>













