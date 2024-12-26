<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// get방식은 한글정상출력, post방식은 한글 깨짐
	// 한글이 깨지기 때문에 문자셋을 인코딩을 해야 한다.
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	String age = request.getParameter("age");
	String[] pets = request.getParameterValues("pet");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<h3>전달받은 파라미터보기</h3>
	이름 : <%= name %><br>
	나이 : <%= age %><br>
	주소 : <%= addr %><br>
	동물 : <%= pets %><br>
	동물1 : <%= pets[0] %><br>
	동물2 : <%= pets[1] %><br>
	동물3 : <%= pets[2] %><br>
	<hr />
	
	이름 : <%= request.getParameter("name") %><br>
	좋아하는 동물들
	<%
		if(pets != null){
			for(int i=0;i<pets.length;i++) {
				// System.out.println(pets[i]);
	%>
				<%= pets[i] %><br>
	<%
			}
		}
	%>
	<hr />
	
	<b>1.request.getParameter() 사용</b><br>
	name 파라미터 = <%= request.getParameter("name") %><br>
	addr 파라미터 = <%= request.getParameter("addr") %><br>
	
	<b>2.request.getParameterValues() 사용</b><br>
	<%
		pets = request.getParameterValues("pet");
		if(pets != null){
			for(int i=0;i<pets.length;i++) {
	%>
				<%= pets[i] %><br>
	<%
			}
		}
	%>
	
	<b>3.request.getParameterNames() 사용</b><br>
	<%
		Enumeration paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String name1 = (String) paramNames.nextElement();
	%>
			<%= name1 %><br>
	<%
		}
	%>
	
	<b>4.request.getParameterMap() 사용</b><br>
	<!-- 
		name=홍길동
		age=100
		addr=조선한양
		pets={dog, cat, pig} 
	-->
	<%
	    Map<String, String[]> map = request.getParameterMap();
		String[] paramValue = (String[]) map.get("pets");
		if(paramValue != null) {
	%>
			파라미터값의크기 : <%= paramValue.length %><br> 
			파라미터의 값: <%= paramValue[0] %> 
	<%
		}
	%>
</body>
</html>


















