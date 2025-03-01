<%@page import="com.lec.member.MemberVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	
	MemberVO member = new MemberVO();
	// member.setXxxx() <- request정보
	member.setId(id);
	member.setName(name);
	member.setEmail(email);	
	member.setPassword("12345");	
	
	request.setAttribute("hong", member);    // member객체를 hong이라는 req속성으로 저장
	request.setAttribute("addr", "조선한양");// String객체를 addr라는 속성을 저장
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%= id %><br>
	<%= name %><br>
	<%= email %><br>
	<hr />
	
	<!-- getter : member.getXxxx() -->
	<h1>회원가입성공</h1>
	<table border="1" width="400" cellpadding="0" cellspacing="0">
		<tr>
			<td>아이디</td>
			<td><%= member.getId() %></td>
			<td>비밀번호</td>
			<td><%= member.getPassword() %></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%= member.getName() %></td>
			<td>이메일</td>
			<td><%= member.getEmail() %></td>
		</tr>
	</table>	
	<hr />
	request.setAttribute -> request.getAttribute<br>
	<%= request.getAttribute("hong").toString() %><br>
	<%= ((MemberVO) request.getAttribute("hong")).getId() %><br>
	<%= request.getAttribute("addr") %><br>
	
	
</body>
</html>