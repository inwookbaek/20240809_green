<%@page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	JSP페이지의 구성요소
	
	1. 디렉티브(Directive)
	
	   디렉티브는 JSP페이지에 대한 설정정보를 지정할 때 사용된다.
	   선언방법은 %@ 디렉티브이름 속성1="값1" 속성2="값2"... %>의 형태로 선언한다.
	   JSP가 제공하고 있는 디렉티브는
	     1) page : jsp페이지에 대한 정보를 지정. jsp가 생성하는 문서타입, 버퍼크기,
	               에러페이지등 jsp페이지에서 필요로 하는 정보를 설정한다.
	     2) taglib : jsp페이지에서 사용할 태그 라이브러리를 지정한다.
	     3) include : jsp페이지의 특정영역으로 다른 문서를 포함한다.(ex: 헤더, 풋터페이지)
	
	2. 스크립트(Script) 
	   
	   JSP에서 문서의 내용을 동적으로 생성하기 위해 사용되는 것으로 스크립트요소를 사용
	   하면 사용자가 폼에 입력한 정보를 데이터베이스에 저장할 수 도 잇고 DB에서 정보를
	   읽어와서 출력할 수도 있다. 스크립트를 사용하면 자바가 제공하는 다양한 기능을 사용
	   할 수 있다. JSP를 스크립트언어라고 불리우는 이유가 바로 막강한 스크립트코드를
	   제공하기 때문이다.
	   
	   JSP의 스크립트요소는 아래와 같다.
	    
	   1) 스크립트릿(Scriptlet) : 자바코드를 실행한다.       < % %>
	   2) 표현식(Expression) : 값을 출력한다.                < %= %>
	   3) 선언부(Declaration) : 자바메서드(함수)를 선언한다. < %! %>  
	   
	   
	3. 기본객체(Implicit Object)
	
	   JSP는 웹애플리케이션 프로그래밍을 하는데 필요한 기능을 기본적으로 제공해주는
	   기본객체를 제공한다. 아래와 같은 객체이외에 다수의 기본객체가 있다.
	   
	   1) request(HttpServletRequst) : 웹브라우저가 웹서버에 전송한 요청들의 정보를 제공
	   2) response(HttpServletResponse) : 웹브라저에 전송하는 응답정보를 제공
	   3) out(JspWriter) : DOM을 출력
	   4) page(PageContext)
	   5) session(HttpSession) 
	   6) application(ServletContext) 
	   	   
	4. 표현언어(Expression Language, EL)
	   
	   JSP스크립트요소는 자바문법을 그대로 사용할 수 있다는 장점이 있지만 JSP코드가 좀
	   복잡해진다. 이런 단점을 보완하기 위해 표현언어를 사용한다 표현언어(EL)의 선언방법
	   은 a * b = ${param.a * param.b}의 형태로 ${와 }사이에 정해진 문법에 따라 정의한다.
	   
	5. 정적인 데이터
	6. 표준액션태그(Action Tag)
	7. 커스텀태그(Custom Tag)
	8. 표준라이브러리(JSTL)
	   	   
-->
</body>
</html>












