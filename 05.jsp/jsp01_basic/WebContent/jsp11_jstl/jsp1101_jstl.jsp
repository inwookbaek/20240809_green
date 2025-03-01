<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
	JSTL?
	
	JSP는 스크립트릿과 표현식등의 스크립트코드와 HTML코드가 뒤섞여서 사용하기 때문에
	JSP코드는 가독성이 떨어지고 코딩하기가 복잡하게 되어 개발이 편리하지 않게 되었다.
	
	JSP는 새로운 태그를 표준라이브러리 형태로 제공하여 보단 쉽게, 가독성이 좋게 코딩
	할 수 있도록 제공하는데 JSP페이지에서 많이 사용되는 논리적인 판단, 반복처리, 포맷
	처리를 할 수 있도록 표준으로 만들어서 정의한 것이 JSP Standard Tag Library이다.
	
	[JSTL이 제공하는 태그의 종류]
	
	라이브러리		주요기능		접두어	관련 URI
	--------------- --------------- ------ -------------------------------------------
	코어		    변수지원	      c    http://java.sun.com/jsp/jstl/core 
					흐름제어 
					URL처리
					
	XML             XML코어 		  x    http://java.sun.com/jsp/jstl/xml
		            흐름제어
		            XML변환
		            
	국제화	        지역			 fmt   http://java.sun.com/jsp/jstl/fmt	
		            메시지형식
	          		숫자, 날짜형식	 
	
	DB	        	SQL			 	 sql   http://java.sun.com/jsp/jstl/sql	
		            메시지형식

	함수	        컬렉션처리		 fn    http://java.sun.com/jsp/jstl/functions	
		            String처리



	[코어태그]
	
	기능		태그		설명
	----------- ------- 	--------------------------------------------------------------
	변수지원	set     	JSP에서 사용할 변수를 설정
				remove  	설정한 변수를 삭제
	
	흐름제어	if 			조건에따라 흐름을 제어
				choose 		다중조건을 처리 
				forEach 	컬렉션이나 Map의 각 항목을 처리할 때 사용
				forTokens 	구분자로 분리된 토큰을 처리
			
	url처리 	import 		URL에 해당되는 자원을 삽입
	            redirect 	지정경로로 리다이렉트 실행
	            url 		URL을 재작성

	기타태그	catch 		익셉션처리 수행
				out 		JspWriter에 내용을 출력
	

	[국제화태그]
	
	국제화태그는 특정지역에 따라 알맞은 메시지를 출력해야 할 때 사용한다. 예를 들어
	한글브라우저로 접속하면 한글메시지를 출력하고 영문브라우저로 접속하면 영문메시지를
	출력해야 할 때 국제화 태그를 사용한다.
	
	국제화태그 라이브러리
	
	기능		태그			설명
	----------- -------------- 	--------------------------------------------------------------
	로케일지정  setLocale 		Locale을 지정
			    requestEncoding 요청파라미터의 캐릭터 인코딩을 지정
			    
	메시지처리	bundle 			사용할 번들지정
				message 		지역에 알맞은 메시지를 출력
				setBundle 		리소스번들을 읽아와 특정변수에 저장
				
	포맷팅		formatNumber 	숫자를 포맷
				formatDate 		일자를 포맷
				parseDate 		문자열로 표시된 날자를 Date객체로 파싱
				parseNumber 	문자열로 표시된 숫자를 숫자로 파싱
				setTimeZone 	시간대정보를 특정변수에 저장
				timeZone	    시간대를 저장
				
	[로케일지정태그]
	
	<fmt:setLocale> : 국제화 태그들이 사용할 로케일을 지정
	<fmt:requestEncoding> : 요청파라미터의 케릭터 인코딩을 지정
	
	웹브라우저는 Accept-Language헤더를 사용해서 수용 가능한 언어목록을 전송하는 데
	JSTL의 국제화 태그들은 이 헤더의 값을 사용해서 언어별로 알맞은 처리를 하게된다.
	예를 들오 메시지를 출력해주는 message태그는 Accept-Language의 값이 'ko'인 경우
	한글메시지를 우선적으로 처리하고 'en'인 경우 영문메시지를 우선적으로처리한다.
	
	국제화태그가 Accept-Language헤더에서 지정한 언어가 아닌 다른 언어를 사용하도록
	지정할 때 사용하는 것이 <fmt:setLocale>태그이다.
	
	[리소스번들]
	
	메시지태그에서 사용할 리소스번들파일을 작성할 때 메시지번들 파일은 클래스패스에
	위치해야 하기 때문에 WEB-INF\classes폴더나 WEB-INF\lib에 포함된 jar파일에 포함
	시켜야 한다.
	
    리소스 번들파일은 기본적으로 java.util.Properties클래스를 사용해서 읽어 오기 때문에 확장자가
    .properties이다. 리소스번들파일에서 중요한 점은 리소스번들파일의 이름을 정해진 규칙으로 작성
    해야한다. "번들이름_언어_국가.properties"의 형식으로 작성해야 하고 국가코드는 생략할 수 있다.

	[메시지처리태그]
	
	<fmt:bundle> : 태그몸체에서 사용할 리소스번들을 지정
	<fmt:message> : 메시지를 출력
	<fmt:setBundle> : 특정메시지를 사용할 수 있도록 로딩
	

	[숫자, 날짜 포맷팅 태그]
	
	관련태그
	
	1. 숫자출력과 파싱 : <fmt:formatNumber>, <fmt:parseNumber>
	2. 날자출력과 파싱 : <fmt:formatDate>, <fmt:parseDate>
	3. 시간대설정 : <fmt:setTimeZone>, <fmt:timeZone>
	
--%>
JSTL 설정

1. jstl-?.?.jar를 maven repository에서 다운<br>
2. Build Path > Add Library
3. 태그라이브러리를 정의