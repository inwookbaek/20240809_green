<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	Session사용하기
	
	웹브라우저에 정보를 보관할 때 쿠키를 사용한다면 세션은 웹컨테이너에 정보를 보관할 때
	사용한다. 세션은 오직 서버에만 생성이 된다.
	
	웹컨테이너는 기본적으로 한 웹브라우저마다 한 세션을 생성한다. 웹브라우저마다 세션이
	따로 존재하기 때문에 세션은 웨브라우저와 관련된 정보를 저장하기에 사용한다.
	즉, 쿠키가 클라이언트측의 데이터저장소라면 세션은 서버측의 저장소이다. 
	
	세션을 생성하면 session기본객체를 이요해 세션을 사용할 수 있게 된다.
	
	JSP에서 세션을 생성하려면 page디렉티브의 session의 속성을 true(기본값)로 지정하면 된다.
	
	[Session 기본객체]
	
	session기본객체는 request기본객체와 마찬가지로 속성을 제공하고 setAttribute(), 
	getAttribute() 메서드를 사용하여 속성값을 읽거나 저장할 수 있다.
	
	session기본객체는 세션만의 고유정보를 제공하기 위해 아래와 같은 메서드가 추가로 있다.
	
	1. getId() : 세션ID(고유의 ID)를 리턴
	2. getCreationTime() : 세션이 생성된 시간을 리턴
	3. getLastAccessedTime() : 웨브라우저가 마지막에 접근한 시간을 리턴
	
	[Session 기본객체의 속성사용]
	
	한번 생성된 세션은 지정한 유효시간동안 유지가 되기 때무에 웹애플리케이션을 실행하는
	동안 지속적으로 사용해야 하는 데이타의 저장소로 세션을 사용한다.
	request기본객체가 하나의 요청을 처리하는데 사용되는 JSP페이지사이에서 공유한다면
	session기본객체는 웹브라우저의 여러요청을 처리하는 JSP페이지에서 공유된다.
	
	로그인한 회원정보등 웨브라우저와 일대일로 관련값을 저장할 때에는 쿠키보다는 세션을
	사용하는 것이 좋다.
	
	[쿠키 vs 세션]
	
	쿠키대신 세션을 사용하는 가장 큰 이유는 세션이 쿠키보다 보안에서 더 좋다는 점이다.
	쿠키의 이름이나 값은 네트워크를 통해 전달되기 때문에 http프로토콜을 사용하는 경우
	중간에 누군가가 쿠키 값을 가로챌 수가 있지만 세션의 값은 오직 서버에만 저장되기 
	때문에 중요한 자료를 보관할 때 알맞은 저장소 이다.
	
	세션을 사용하는 두번째 이유는 웹브라우저가 쿠키를 지원하지 않을 경우 또는 강제적으로
	쿠키를 사용하지 못하게 할 경우 세션은 쿠키설정여부와 상관없이 사용할 수 있다.
		
	[세션종료]
	
	세션을 유지할 필요가 없다면 session.invalidate()메서드를 사용해서 세션을 종료한다.
	세션을 종료하면 현재 사용중인 session기본객체를 삭제하고 session기본객체에 저장했던
	속성목록도 삭제된다.
	
	[세션유효시간]
	
	세션은 최종접근시간정보를 갖고 있는데 session기본객체를 사용할 때마다 최종접근시간이
	갱신된다. 
	
	세션은 마지막 접근 이후 일정시간 이내에 다시 세션에 접근하지 않는 경우 자동으로 세션을
	종료하는 기능을 갖고 있다.
	
	세션유효시간설정은 아래와 같이 두가지 방법이 있다.
	
	1. web.xml에 <session-config>태그를 사용하여 지정 (분단위)
	2. setMaxInactivInterval() 메서드 사용 (초단위)
	
	[request.getSession()을 이용한 세션생성]
	
	HttpSession을 생성하는 또 다른 방법은 request기본객체의 getSession()메서드를 이용하는
	것이다. 이 메서드는 현재요청과 관련된 session객체를 리턴하는데 session이 존재하면
	해당 session를 리턴하고 없을 경우 새롭게 session을 생성해서 리턴한다.
	
	session이 생성된 경우에만 session객체를 구하고 싶을 경우 매개값으로 false를 전달하면
	세션이 존재하는 경우만 리턴하고 없을 경우 null을 리턴한다.
		
-->
<%@page session="true" %>
<%
	Date time = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>세션정보조회하기</h1>
	세션ID = <%= session.getId() %><br>
	<%
		time.setTime(session.getCreationTime());
	%>
	세션생성시간 = <%= sdf.format(time) %><br>
	<%
		time.setTime(session.getLastAccessedTime());
	%>
	세션마지막접속시간 = <%= sdf.format(time) %>
</body>
</html>















