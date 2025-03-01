<%@page import="java.util.Enumeration"%>
<%@page import="org.apache.catalina.core.AprLifecycleListener"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	application기본객체
	
	application기본객체는 웹애플리케이션 전반에 걸쳐서 사용되는 정보를 담고 있다.
	예를 들어 application기본객체를 사용해서 초기설정정보, 서버정보, 웹애플리케이션이 
	제공하는 파일들을 읽어 올 수 있다.
	
	서블릿규약은 웹애플리케이션 전체에 걸쳐 사용할 수 있는 초기화 파라미터를 정의하고
	있는데 초기화 파라미터는 WEB-INF/web.xml파일에 <context-param>태그를 사용하여 추가한다.
	
	<web.xml>? 
	web.xml파일은 웹애플리케이션을 위한 설정정보를 가지고 있는 파일이다. JSP프로그래밍을
	할 때 꼭 필요한 파일은 아니지만 환경설정파일로서 중요한 파일이다.
	web.xml은 웹애플리케이션폴더/WEB-INF폴더에 위치해야 한다. 다른 폴더에 위치하면 파일을
	인식하지 못한다.
	
	application기본객체의 초기화 메서드
	
	1. getInitParameter(String name) : 이름이 name인 웹애플리케이션 초기화 파라미터 값을 읽음
	2. getInitParameterNames() : 웹애플리케이션 초기화 파라미터 이름목록을 리턴
	
	application기본객체의 서버관련 메서드
	
	1. getServerInfo() : 서버정보를 리턴
	2. getMajorVersion() : 서버가 지원하는 서블릿규약의 메이저버전을 리턴
	3. getMinorVersion() : 서버가 지원하는 서블릿규약의 마이너버전을 리턴
	
	application기본객체의 로그기록관련 메서드
	 
	1. log(String msg) : msg를 로그로 기록
	2. log(String msg, Throwable throwable) : msg로그기록과 익셉션정보도 같이 기록
		
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>application 초기파라미터</h1>
	<ul>
	<%
		Enumeration<String> initParam = application.getInitParameterNames();
		while(initParam.hasMoreElements()) {
			String paramName = initParam.nextElement();
	%>
			<li><%= paramName %> = <%= application.getInitParameter(paramName) %></li>
	<%
		}
	%>
	</ul>
	<hr />
	
	<h3>application 서버정보</h3>
	서버정보 : <%= application.getServerInfo() %><br>
	서블릿메이저버전 : <%= application.getMajorVersion() %><br>
	서블릿마이너버전 : <%= application.getMinorVersion() %><br>
	요청CharacterSet : <%= application.getRequestCharacterEncoding() %><br>
	응답CharacterSet : <%= application.getResponseCharacterEncoding() %><br>
	
	<h3>log 메시지 기록</h3>
	<%
		application.log("Application Log Message 기록하기!!");
		log("로그메시지기록 - log");
	%>
</body>















</html>