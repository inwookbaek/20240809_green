<%@page import="com.lec.ex03_connector.JDBCConnector"%>
<%@page import="java.net.URLEncoder"%>
<%@page pageEncoding="UTF-8"%>
<%
    // utf-8 ->  ./jsp0209_form.jsp?name=%EC%9E%90%EB%B0%94
    // euc-kr -> ./jsp0209_form.jsp?name=%C0%DA%B9%D9
	String value = "자바";
//	String encodingValue = URLEncoder.encode(value, "utf-8");
	String encodingValue = URLEncoder.encode(value, "euc-kr");
	response.sendRedirect("jsp0209_form.jsp?name=" + encodingValue);
%>
<!-- 
	실습. 로그인 form을 만들어서 MySQL.member의 회원ID를 읽어서
	회원정보를 출력하는 웹애플리케이션을 작성
	Connection, PreparedStatement, ResultSet
	1. ex01_login.jsp : id, submit(전송)
	2. ex02_success.jsp : 
	   1) request.getParament("id")
	   2) Connection, PreparedStatement, ResultSet
	      sql = "select * from member"
	      rs.next
	      rs.getInt, rs.getString
	  
	  회원아이디: hong<br>
	  회원이름  : 홍길동<br>
	  회원성별<br>
	  회원...<br>	
-->


<%
	
	JDBCConnector.getConnection();

%>















