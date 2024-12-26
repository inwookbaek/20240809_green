<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	표현언어(Expression Language = EL) 
	
	표현언어(는 다른 형태의 스크립트언어로서 스크립트요소중의 하나이다. 표현언어는 표현식
	보다 간결하고 편리하기 때문에 많이 사용된다.
	
	EL은 JSTL(JSP Standard Tag Library)규약에 나오는 내용으로서 JSP2.0부터 EL이 JSP에 포함
	되었고 EL은 값을 표현하는데 사용하는 언어로서 JSP의 스크립트요소를 보완하는 역할을
	한다.
	
	EL은 아래와 같은 기능을 제공한다.
	
	1. JSP의 네가지 기본객체(application, session, page, request)가 제공하는 영역의 속성
	   ... xxxScope, ...
	2. 수치연산, 관계연산, 논리연사자 제공
	3. 자바클래스의 메서드 호출기능 제공
	4. 쿠키, 기본객체의 속성등 JSP를 위한 표현언어의 기본객체 제공
	5. 람다식을 이용한 함수정의와 실행(EL3.0)
	6. 스트림 API를 통한 컬렉션처리(EL3.0)
	7. 정적메서드 실행(EL3.0)
	
	[EL의 구성]
	
	EL은 $ {expr}의 형태로 사용할 수 있다. EL은 JSP의 스크립트요소(스크립트릿, 표현식,
	선어부)를 제외한 나머지 부분에서 사용할 수 있다. JSP2.1버전부터는 $ {expr}형식과
	# {expr}구문을 지원하고 있는데 차이점은 실제로 EL의 값을 언제 생성하느냐에 있다.
	
	${expr}은 구문을 분석할 때 곧바로 값을 계산하지만 # {expr}은 실제로 값이 사용될 때
	값이 계산된다.
	
	[EL의 데이터타입]

	1. Boolean
	2. 정수 (EL에서 정수 타입은 java.lang.Long 타입이다)
    3. 실수 (EL에서 실수 타입은 java.lang.Double 타입이다)	
    4. 문자열 : " or ' 같이 사용가능 작은따옴표가 포함된 문자열은 \'로 사용해야 되고
       \기호는 \\로 사용해야 한다
    5.널(null)
    
    [EL의 기본객체]
    
    1. pageContext : JSP의 page기본객체와 동일
    2. pageScope : pageContext기본객체에 저장된 속성의 <속성, 값>의 Map객체
    3. requestScope : request기본객체에 저장된 속성의 <속성, 값>의 Map객체
    4. sessionScope : session기본객체에 저장된 속성의 <속성, 값>의 Map객체
    5. applicationScope : application기본객체에 저장된 속성의 <속성, 값>의 Map객체
    6. param : 요청파라미터의 <이름, 값>의 Map객체 / request.getParameter()와 동일
    7. paramValues : 요청파라미터의 <이름, 값배열>의 Map객체 / request.getParameterValues()와 동일
    8. header : 요청정보의 <헤더이름, 값>의 Map객체 / request.getHeader()와 동일
    9. heacerValues : 요청정보의 <헤더이름, 값배열>의 Map객체 / request.getHeaders()와 동일
   10. cookie : <쿠키이름, Cookie>의 Map객체 / request.getCookies()와 동일
   11. initParam : 초기화 파라미터<이름, 값>의 Map객체 / application.getInitParameter()동일

    [객체접근] 
    
    EL언어는 객체에 저장된 값에 접근할 때 점(.)이나 대괄호([])를 사용한다. <표현1>.<표현2>
    또는 <표현1>[표현2]형식으로 접근하는데 접근과정은 아래와 같은 순서로 변환이 된다.
    
    
    1. <표현1>을 <값1>로 변환
    2. <값1>이 null 이면 null을 리턴
    3. <값1>이 null아닐 경우 <표현2>의 <값2>를 반환
    4. <값1>이 Map, List, 배열일 경우
       1) <값1>이 Map일 경우
          (1) <값1>.containsKey(<값2)>이 false이면 null을 리턴
          (2) 아니면 <값1>.get(<값2>)를 리턴  
       2) <값1>이 List나 배열일 경우       
          (1) <값2>가 정수값인지 검사(정수가 아니면 에러발생)
          (2) <값1>.get(<값2>) 또는 Arrays.get(<값1>, <값2>)를 리턴
          (3) 상기코드가 익셉션일 경우 에러 발생
    5. <값1>이 다른 객체이면
       1) <값2>를 문자열로 변환
       2) <값1>객체가 <값2>를 이름으로 갖는 읽기 가능 프로퍼티를 포함하고 있다면
          프로퍼티의 값을 리턴
       3) 그렇지 않을 경우 에러 발생
       
    [수치연산자] 
    
    +,-,*,/(div), %(mod), -단항연산자
    
    예를 들어 
    $ {"10" + 1} -> 11(자바는 "101")
    $ {"일" + 1} -> 에러발생 (자바는 "일1")
    $ {null + 1} -> 1
    
    [비교연산자]  
    
    ==(eq), !=(ne), <(lt), >(gt), <=(le), >=(ge)
    
    [논리연산자]
    
    &&(and), ||(or), !(not)
    
    [empty연산자]
    
    emtpty <값> 형태로 사용
    
    리턴되는 값이 결정되는 순서
    
    1. <값> null이면 true 리턴
    2. <값>이 빈문자열("")이면 true리턴
    3. <값>길이가 0이면 true리턴
    4. <값>이 빈 Map이면 true 리턴
    5. <값>이 빈 Collection이면 true리턴
    6. 이외의 경우는 false리턴
    
    [삼항연산자 = 비교선택연산자]
    
    <수식> ? <값1> : <값2>
    
    [컬렉션]
    
    EL2.2버전은 컬렉션을 위한 표현방식을 지원하지 않기 때문에 컬렉션을 사용하려면 EL2.3
    이후 버전을 사용해야 한다.
    
    [세미콜론연산자]
    
    EL3.0에 추가된 연산자로 두개의 식을 연결할 수 있다. $ {1+1; 10+10} -> 20 만 출력이
    된다. $ {A; B}의 EL은 B값만 출력한다.
    
    [할당연산자]
    
    EL3.0에서부터 제공되는 데 예를 들어 $ {var = 10} -> 10으로 출력         
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	request.setAttribute("name", "홍길동");
%>
</body>
	<h1>request vs requestScope</h1>
	1. JSP request의 name = <%= request.getAttribute("name") %><br>
	2. EL request의 name = ${ requestScope.name }<br>
	
	<h1>request.getParameter vs param</h1>
	<!-- http:/..../jsp1001_useObject.jsp?id=hong&name=홍길동 -->
	1. JSP의 request.getParameter("id") = <%= request.getParameter("id") %><br>
	   JSP의 request.getParameter("name") = <%= request.getParameter("name") %><br>
	2. EL의 param.id = ${ param.id }<br>
	   EL의 param.name = ${ param.name }<br>
	   
	<h1>pageContext</h1>
	1. JSP의 요청URI = <%= pageContext.getRequest().getServerName() %><br>
	2. EL의 요청URI = ${ pageContext.request.requestURI }<br>
</html>















