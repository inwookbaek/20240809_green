package com.lec.ex03_System;

import java.util.Properties;
import java.util.Set;

/*	
	5. 시스템프로퍼티읽기(getProperty(), getProperties())
	
	   시스템프로퍼티는 JVM이 시작될 때 자동으로 설정되는 시스템의 속성값들을 말한다.
	   예를 들어 운영체제의 종료, 자바프로그램을 실행시킨 사용자ID, JVM버전, 운영체제
	   에서 사용되는 팔일경로, 구분자 등이다.
	   시스템프로퍼티는 key와 value으로 구성되어 있다.
*/
public class GetPropertyMain {

	public static void main(String[] args) {
		
		String os_name = System.getProperty("os.name");
		String user_name = System.getProperty("user.name");
		String user_home = System.getProperty("user.home");
		System.out.println("os.name = " + os_name);
		System.out.println("user.name = " + user_name);
		System.out.println("user.home = " + user_home);
		System.out.println();
		
		System.out.println("-".repeat(50));
		System.out.println("    [key]          [value] ");
		System.out.println("-".repeat(50));
		
		Properties props = System.getProperties(); 	// Properties는 Map컬렉션프레임워크의 자료구조중 하나
		Set keys = props.keySet();     				// 컬렉션프레임워크의 Set자료구조
		
		for(Object key : keys) {
			System.out.println(String.format("[ %s ] = %s", (String) key, System.getProperty((String) key)));
		}
	}

}
