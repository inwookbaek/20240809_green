<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>회원정보</h1>
	<table border="1" width="100%">
		<tr>
			<th>이름</th>
			<th>아이디</th>
			<th>이메일</th>
		</tr>
		<%
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try {				
/* 				
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/jspstudy";
				String usr = "root";
				String pwd = "12345"; 
				conn = DriverManager.getConnection(url, usr, pwd);
*/
				// DBCP 이용
				String drv = "jdbc:apache:commons:dbcp:jspstudyPoolName";
				String sql = "select * from member order by id";
				
				conn = DriverManager.getConnection(drv);			
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
		%>
				<tr>
					<td><%= rs.getString("name") %></td>
					<td><%= rs.getString("id") %></td>
					<td><%= rs.getString("email") %></td>				
				</tr>
		<%
				}  // while
			} catch(Exception e) {
				e.getMessage();
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
	</table>
</body>
</html>













