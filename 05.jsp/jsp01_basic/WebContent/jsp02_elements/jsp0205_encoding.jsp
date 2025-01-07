<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	JSP페이지의 인코딩과 pageEncoding속성
	
	JSP파일에서 캐리터셋을 잘못 지정하면 응답결과의 문자들이 깨져서 출력이 된다. 톰캣과
	같은 컨테이너는 JSP를 분석하는 과정에서 어떤 인코딩을 이용해서 코드를 작성했는지를
	검사하고 그 결과로 선택한 캐릭터셋을 이용해서 JSP페이지의 문자를 읽어오게 된다.
	
	웹컨테이너가 JSP페이지를 읽을 때 사용할 캐릭터셋을 결정하는 과정은 아래와 같다.
	
	BOM(Byte Order Mark)? utf-8, utf-32... 등과 같은 유니코드 인코딩에서 바이트의 순서가
	리틀엔디언(little Endian)인지 빅엔디언(Big Endian)인지의 여부를 알려주는 값이다.
	
 
	1. 파일이 BOM으로 시작하지 않을 경우
	
	   1) 기본인코딩을 이용해서 파일을 처음부터 읽고 page디렉티브의 pageEncoding속성검색
	   2) pageEncoding속성이 값을 가지고 있다면, 파일을 읽을 때 속성값을 캐릭터셋으로 사용
	   3) pageEncoding속성이 값이 없다면 contentType속성을 검색
	   4) 상기모두 해당되지 않았을 경우 iso-8859-1을 캐릭터셋으로 사용
	
	2. 파일이 BOM으로 시작할 경우 
	
	   1) BOM을 이용해서 결정된 인코딩을 이용하여 파일을 읽고, page디렉티브의 pageEncoding
	      값을 검색
	   2) 만약,  pageEncoding속성값과 BOM을 이용해서 결정된 인코딩이 다를 경우 에러발생

    3. 1 or 2과정을 통해 설정된 캐릭터셋을 이용해서 JSP소스코드를 읽는다.

-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	현재시간 : <%= new java.util.Date() %>
</body>
</html>