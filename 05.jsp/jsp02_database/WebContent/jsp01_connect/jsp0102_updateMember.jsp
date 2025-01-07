<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.mysql.cj.xdevapi.PreparableStatement"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>회원정보수정성공</h1>
<%-- 	
   회원정보가 성공적으로 수정이 되었습니다!!<br>
	<%= id %><br>
	<%= pwd %><br>
	<%= name %><br>
	<%= email %><br>
--%>
	<% 
		// 실습. 회원수정로직완성하기
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {	
			String url = "jdbc:mysql://localhost:3306/jspstudy";
			String usr = "root";
			String pw = "12345";
			String sql = "update member set pwd=?, name=?, email=? where id=?";
	
			conn = DriverManager.getConnection(url, usr, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			
			int row = pstmt.executeUpdate();
%>
			<h3 style="color: blue;"><%= id %>에 회원정보가 성공적으로 수정되었습니다!!</h3>		
<%
		} catch(Exception e) {
%>
			<h3 style="color: red;"><%= id %>에 해당되는 회원정보를 찾지 못했습니다</h3>			
<%
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e1) {
				// dummy
			}
		}
	%>
</body>
</html>







