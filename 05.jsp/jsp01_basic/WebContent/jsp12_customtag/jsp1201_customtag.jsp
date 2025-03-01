<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  
	커스텀태그(Custom Tag)
	
	프로그램을 개발하다 보면 JSP액션태그나 JSTL 태그만으로 부족할 때가 있는데 이를 지원하기 위해서
	JSP는 새로운 태그를 만들어서 사용할 수 있도록 제공하는 기능이 있다. 사용자가 원하는 목적에 맞게
	작성한 태그를 커스텀태그라고 한다.
	
	1. 커스텀태그의 장점
	
	   1) 재사용 : 한번 작성한 커스텀 태그는 어떤 JSP컨테이너에서도 사용이 가능한다.
	   2) 쉽고 단순한 JSP코드 작성: 자바코드에 익숙하지 않은 개발자들도 커스텀태그를 사용하면
	      보다 쉽게 JSP페이지를 작성할 수 있게 된다.
	   3) 코드 가독성 향상 : 커스텀 태그를 사용하면 스크립트코드를 줄일 수 있기 때문에 JSP의 가독성
	      을 높일 수 있다.
	      
	2. 태그파일에서 사용가능한 디렉티브
	
		1) tag : JSP페이지의 page디렉티브와 동일
		2) taglib : 태그파일에서 사용할 태그라이브러리를 명시
		3) include : 태그파일에 특정파일을 포함 시킬 때 사용
		4) attribute : 태그 파일을 커스텀태그로 사용할 때 입력받을 속성을 명시
		5) variable : EL 변수로 사용할 변수에 대한 정보를 정의
	
	3. 태그디렉티브의 속성
	
	   1) display-name : 태그파일을 도구에서 보여줄 때 사용될 이름을 지정 기본값은 확장자 ".tag"를
	      제외한 태그파일의 나머지 이름
	   2) body-content : 몸체내용의 종류를 정의, empty, tagdependent, scriptless의 세가지값 중 하나
	      를 사용할 수 있다. 기본값은 scriptless
	   3) dynamic-attributes : 동적속성을 정의, 속성의 <이름,값>을 저장하는 Map객체를 page범위의
	      속성에 저장할 때 사용할 이름을 정의
	   4) description : 태그에 대한 설명
	   5) import : page디렉티브의 import속성과 동일
	   6) pageEncoding : page디렉티브의 pageEncoding속성과 동일
	   7) isELIgnored : page디렉티브의 isELIgnored속성과 동일
	   8) deferredSyntaxAllowedAdLiteral : page디렉티브의 deferredSyntaxAllowedAdLiteral속성과 동일
	      이 값이 true이면 ${expr} or #{expr} 형식의 값은 문자로 처리
	   9) trimDirectiveWhitespaces : page디렉티브의 trimDirectiveWhitespaces속성과 동일
	
	4. 태그파일에서 사용가능한 기본객체
	  
	   1) jspContext : jsp페이지의 pageContext가 제공하는 메서드를 그대로 제공, 각 속성과 관련된 작업을 처리
	   2) request : jsp페이지의 request와 동일
	   3) response : jsp페이지의 response와 동일
	   4) session : jsp페이지의 session와 동일
	   5) apllication : jsp페이지의 apllication와 동일
	   6) out : jsp페이지의 out와 동일
	
	5. 태그파일의 위치는 WEB-INF/tags폴더나 그 하위폴더에 위치한다. 이 폴더에 위치한 파일 중에서
	   .tag, .tagx 확장자를 갖는 파일만 태그파일로 인식한다.
	
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

</body>
</html>