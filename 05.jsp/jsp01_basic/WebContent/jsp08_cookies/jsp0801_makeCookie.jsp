<%@page import="java.net.URLEncoder"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	[쿠키(Cookie) 사용하기] 
	
	쿠키(Cookie)는 웹브라우저가 보관하는 데이터이다. 웹브라우저는 웹서버에 요청을 보낼 때
	쿠키를 함께 전송하고 웹서버는 웹브라우저가 전송한 쿠키를 사용해서 필요한 데이터를 처리
	할 수가 있다. 쿠키는 웹서버와 웹브라우저 양쪽에서 생성할 수 있는데 JSP에서 생성하는
	쿠키는 웹서버에서 생성하는 쿠키이다.
	
	[쿠키의 동작방식(3단계)]
	
	1. 쿠키생성단계 : 쿠키를 사용하려면 먼저 쿠키를 생성해야 하고 JSP프로그래밍에서 쿠키는
	   웹서버측에서 생성한다. 생성한 쿠키는 응답데이터의 헤더에 저장해서 웹브라우저로 
	   전송한다.
	2. 쿠키저장단계 : 웹브라우저는 응답데이터에 포함된 쿠키를 쿠키저장소에 보관하며 쿠키
	   의 종류에 따라 메모리나 파일에 저장한다.
	3. 쿠키전송단계 : 웹브라우저는 저장한 쿠키를 요청이 있을 때마다 웹서버에 전송한다.
	   웹서버는 웨브라우저가 전송한 쿠키를 사용해서 필요한 작업을 수행한다.
	   
	
	[쿠키의 구성요소]
	
	1. 이름 : 각각의 쿠키를 구별하는 데 사용되는 이름
	2. 값 : 쿠키의 이름과 관련된 값
	3. 유효시간 : 쿠키의 유지시간
	4. 도메인 : 쿠키를 전송할 도메인
	5. 경로 : 쿠키를 전송할 요청 경로
	
	[쿠키의 네이밍룰]
	
	쿠키의 이름은 콤마(,), 세미콜론(;), 등호기호(=)를 제외한 알파벳과 숫자로 조합된 문자
	로 정의할 수 있으며 '$'로 시작할 수 없다.
	
	[쿠키의 생성]
	
	JSP에서 쿠키를 생성할 때 Cookie클래스를 사용하는데 생성자의 매개값으로 쿠키이름, 쿠키값
	을 아래와 같이 정의하여 생성한다.
	
	Cookie cookie = new Cookie("쿠키이름", "쿠키값");
	response.addCookie(cookie); 

    Cookie클래스가 제공하는 메서드
    
    1. getName() : 쿠키이름을 리턴
    2. getValue() : 쿠키값을 리턴
    3. setValue(String value) : 쿠키값을 저장
    4. setDomain(String pattern) : 쿠키가 전송괼 서버의 도메인을 지정
    5. getDomian() : 쿠키의 도메인을 리턴
    6. setPath(String url) : 쿠키를 전송할 경로를 지정
    7. getPath() : 쿠키의 전송경로를 리턴
    
    [Cookie값을 읽어오기]
    
    쿠키를 생성한 후에 해당 쿠키를 사용할 수 있는데 웹브라우저는 요청헤더에 쿠키를 저장
    해서 전송하며 JSP는 아래코드를 사용해서 쿠키값을 읽어 온다.
    
    Cookie[] cookies = request.getCookies();
    
    request.getCookies()메서드는 쿠키배열을 리턴하고 읽어올 쿠기값이 존재하지 않으면 
    null을 리턴한다. 
    
    [쿠키값 변경 및 삭제]
    
    쿠키값을 변경하려면 같은 이름의 쿠키를 새로 생성해서 응답데이타로 전송하면 변경이
    된다. 예를 들어 아래와 같이
    
    Cookie cookie = new Cookie("쿠키이름", URLEncoder.encode("새로운값", "utf-8");
    response.addCookie(cookie)
    
    쿠키가 존재하지 않으면 쿠키가 생성되기 때문에 쿠키의 존재여부를 확인해서 존재할 경우
    값을 변경해 주면 된다.
    
    쿠키를 삭제하려면 쿠키를 삭제하는 기능을 별도로 제공하지 않고 setMaxAge()메서드를 
    호출해서 인자값을 0으로 지정하면 삭제가 된다. 
    
    
    
	[쿠기의 도메인]
	
	기본적으로 쿠키는 그 쿠기를 생성한 서버에만 전송이 된다. 예를 들어 "http://www.google.com"
	에 연결해서 생성된 쿠키는 다른 사이트로 연결될 때에는 전송되지 않고 해당 사이트에 연결
	할 때만 전송된다. 하지만 같은 도메인을 사용하는 모든 서버에 코키를 보내야할 경우가 
	있는 데 예를 들어 "http://www.google.com"에서 생성한 쿠키를 mail.google.com과 기타 동일
	도메인에 포함된 서버에 전송할 때 가 있다. 
	
	이럴 경우 setDomain()메서드를 사용해서 전송할 수 있다. 도메인의 지정은 다음과 같이
	
	1. .google.com : 점으로 시작하는 경우 관련 도메인 모두 쿠키를 전송한다.
	2. mail.google.com : 특정도메인에 대해서만 쿠키를 전송한다.
	
	도메인을 지정할 때 주의할 점은 setDomain()의 값으로 현재서버의 도메인 및 상위도메인만
	전달하 수 있다.	
	
	[쿠키의 경로]
	
	도메인이 쿠키를 공휴할 도메인의 범위를 지정한다면 경로는 쿠키를 공유할 기준경로를
	지정한다. 쿠키의 경로를 지정할 때에는 setPath()메서드를 사용한다. 경로는 URL에서 
	도메인 이후의 부분에 해당한다. 
	예를 들어 http://localhost:8080/jspstudy/path2/makeCookie.jsp의 URL에서 경로는 
	/jspstudy/path2이다. 이 URL에서 경로는 "/"나 "/jspstudy" 또는 "/jspstudy/path2"를
	사용할 수 있다.
		
	[쿠키의 유효시간]
	
	쿠키는 유효시간을 갖는다. 쿠키의 유효시간을 지정하지 않으면 웹브라우저를 종료할 때
	쿠키를 함께 삭제한다. 웹브라우저 종료후 다시 웨브라우저를 실행하면 삭제한 쿠키는 
	서버에 전송되지 않는다. 쿠키의 유효시간을 정해 놓으면 으 유효시간 동안 쿠키가 존재하고
	웨브라우저를 종료해도 유효시간이 지나지 않았다면 쿠키를 삭제하지 않는다.
	
	유효시간을 지정하려면 setMaxAge()메서드를 사용한다. 유효시간은 초단위로 지정한다.
	
	[쿠키와 헤더]	
	
	response.addCookie()로 쿠키를 추가하면 실제고 "Set-Cookie"헤더를 통해서 전달 된다.
	한개의 "Set-Cookie"헤더는 한개의 쿠키값을 전달한다. 
	"Set-Cookie"헤더의 구성은 다음과 같다.
	
	Set-Cookie:쿠키이름=쿠키값;Domain=도메인값;Path=경로값;Expores=GMT형식의 만료일시
	
-->
<%
	Cookie cookie = new Cookie("name", URLEncoder.encode("홍길동", "utf-8"));
	response.addCookie(cookie);
	response.addCookie(new Cookie("addr", "조선한양"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Cookie 생성하기</h1>
	<%= cookie.getName() %> 쿠기의 값 = "<%= cookie.getValue() %>"
</body>
</html>