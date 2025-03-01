<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index.jsp with MySQL</h1>
	MySQL사용할 경우(bundle.gradle 의존주입)
	<ol>
		<li>lombok : <br>
			lombok 설치 : https://projectlombok.org/download<br>
			c:\>java -jar lombok.jar<br>
			compileOnly 'org.projectlombok:lombok'<br>
			annotationProcessor 'org.projectlombok:lombok'
		</li>
		<li>MySQL  : runtimeOnly 'com.mysql:mysql-connector-j'</li>
		<li>test program 작성하기</li>
		<ul>
			<li>src/test/java/ConnectTests.java</li>
		</ul>
		<li>HikariCP : implementation 'com.zaxxer:HikariCP:6.0.0'</li>
		<li>DTO -> VO, VO -> DTO  변환 설정<br>
			VO는 getter만, DTO setter/getter모두 사용<br>
			ModelMapper : implementation 'org.modelmapper:modelmapper:3.2.1'
		</li>
		<li>@Log4j2<br>
			src/main/resources/log4j2.xml작성
		</li>
	</ol>
	<hr />	
</body>
</html>