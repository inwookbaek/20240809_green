<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//~.jsp?id=hong
	String id = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!--  
drv=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/board
usr=root
pwd=12345
-->
	<h1>회원정보조회하기</h1>
	<% 
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mysql://localhost:3306/jspstudy";
			String usr = "root";
			String pwd = "12345";
			String sql = "select * from member where id = '" + id + "'";
			
			conn = DriverManager.getConnection(url, usr, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
	%>
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><%= id %></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><%= rs.getString("pwd") %></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><%= rs.getString("name") %></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><%= rs.getString("email") %></td>
				</tr>
			</table>
	<%
			} else {
	%>
				<h3 style="color: red;"><%= id %>에 해당되는 회원정보를 찾지 못했습니다</h3>
	<%
			}	
		} catch(Exception e) {
	%>
			에러 : <%= e.getMessage() %>
	<%
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e1) {
				// dummy
			}
		}
	%>
</body>
</html>