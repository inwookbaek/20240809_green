<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// MemberVO member = new MemberVO();
%>
<!-- jsp:useBean태그는 객체를 생성해주는 태그 
     즉, MemberVO member = new MemberVO(); 대신에 사용할 수가 있다.
     
     jsp:useBean태그를 이용한 자바객체 생성하기
     
	JSP페이지의 주된 기능은 데이터를 보여주는 기능이다. 게시판의 글목록보기, 글읽기, 회원
	정보등의 데이터를 보여줄 때 JSP에서는 이런 종류의 데이터를 자바빈과 같은 클래스에 담
	아서 보여주는 것이 일반적이다.
	
	이런 기능을 제공하기 위해서 JSP규약은 JSP페이지에서 빈번히 사용되는 자바빈객체를 위한
	액션태그를 제공하는데 이들 액션태그를 사용하면 자바빈객체를 생성하거나, 빈의 프로퍼티
	를 출력 또는 변경할 수 있다. 
	
	JSP에서는 자바빈객체를 생성할 수 있도록 jsp:useBean액션태그 및 jsp:setProperty, 
	jsp:getProperty액션태그를 제공한다
	
	1. jsp:useBean 문법
	
	   jsp:userBean id="빈이름" class="자바빈클래스이름" scope="범위 
	   
	   1) id : JSP페이지에서 자바빈객체를 접근할 때 사용할 이름을 지정
	   2) class: 패키지이름을 포함한 자바빈 클래스의 풀네임을 입력
	   3) scope: 자바빈 객체를 저장할 영역을 지정, page, request, session, application중
	             하나의 값을 지정, 기본값은 page이다.     
-->
<jsp:useBean id="member" scope="request" class="com.lec.member.MemberVO"/>
<% 
	member.setId("hong");
	member.setName("홍길동");
%>
<!-- ?member=member -->
<jsp:forward page="./jsp0702_useObject.jsp"/>














