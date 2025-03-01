<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 실습. 1~10까지 합을 출력해 보기 -->
<% int sum = 0; %>
1~10까지 합은 <%= sum %> 입니다<br>
1~10까지 합은 <%= 1+2+3+4+5+6+7+8+9+10 %> 입니다<br>
<hr />
<%
	for(int i=1;i<=100;i++) {
		sum += i;
	}
%>
1~100까지 합은 <%= sum %> 입니다<br>