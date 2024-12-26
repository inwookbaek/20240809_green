<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="member" scope="request" class="com.lec.member.MemberVO"/>
회원ID   : <%= member.getId() %><br>
회원이름 : <%= member.getName() %><br>
<hr />

<h3>로그인성공!!!</h3>
<%= member.getName() %>님 환영합니다!!!