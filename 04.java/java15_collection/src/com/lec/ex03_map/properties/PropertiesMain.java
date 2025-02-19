package com.lec.ex03_map.properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

/* 
	Properties
	
	Properties는 Hashtable의 하위 클래스이기 때문에 Hashtable의 모든 속성을 그대로 가지고 있는데
	차이점은 Hashtable은 키와 값에 다향한 참조타입으로 정의할 수 있지만 Properties는 키와 값의
	참조타입이 오직 String으로만 정의할 수 있도록 제한된 Map컬렉션이다.
	
	Properties는 애플리케이션의 옵션정보, 데이터베이스이 연결정보, 그리고 국제화(다국어)정보가
	제공된 프로퍼티파일을 읽을 때 주로 사용한다.
	
	Properties파일은 키와 값이 "="로 연결되어 있는 텍스트파일로 ISO8859-1문자셋으로 저장된다.
	이 문자셋으로 직접 표현할 수 없는 값 즉, 한글은 유니코드(\u9999)로 변환되어 저장된다. 
	이클립스에서는 유니코드로 변환된 내용을 한글로 확인하려면 마우스를 유니코드위에 올려 놓으면
	한글을 볼 수가 있다.
	
	이클립스를 사용하지 않는다면 한글이 포함된 Properties파일을 다른 에디터(예를 들어 notepad)
	에서 작성하고 JDK설치 폴더에 native2ascii.exe프로그램을 이용해서 ISO8859-1파일로 저장하면
	된다.
*/
public class PropertiesMain {

	public static void main(String[] args) throws Exception  {
		// Properties파일읽기
		// 1. 파일경로확인
		// D:\lec\04.java\java15_collection\src\com\lec\ex03_map\properties\database.properties
		String path = PropertiesMain.class.getResource("database.properties").getPath();
		// 경로명에 한글이 있을 경웨는 decoding, UnsupportedEncodingException처리를 해야 한다.
		path = URLDecoder.decode(path, "utf-8");
		System.out.println("datab.properties 경로 = " + path);
		System.out.println();
		
		// 2. 파일로딩
		Properties properties = new Properties();
		properties.load(new FileReader(path)); // FileNotFoundException, IOException
		
		// 3. 파일읽기 (Properties가 Hashtable을 자식객체)
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String hangul = properties.getProperty("hangul");
		String name = properties.getProperty("name");
		String menu = properties.getProperty("menu");
		
		System.out.println("driver = " + driver);
		System.out.println("url = " + url);
		System.out.println("user = " + user);
		System.out.println("password = " + password);
		System.out.println("hangul = " + hangul);
		System.out.println("name = " + name);
		System.out.println("menu = " + menu);
	}
}