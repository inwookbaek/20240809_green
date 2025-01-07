package com.lec.ex05_String.token;

import java.io.StreamTokenizer;
import java.util.StringTokenizer;

/*
	StringTokenize클래스
	
	문자열이 특정 구분자(delimiter)로 연결이 되어 있는 경우 구분자를 기준으로 문자열을
	분리하기 위해서 String.split(regex)을 이용하거나 java.lang.StringTokenizer클래스를
	이용할 수 있다.
	
	String.split(regex)은 정규표현식으로 분리하지만 StringTokenizer()는 문자(구분자)로
	구분한다. 문자열이 한 종류의 구분자로 연결이 되어 있을 경우에는 StringTokenizer를
	이용하면 손쉽게 문자열(token)을 분리할 수가 있다.
	
	StringTokenizer의 메서스
	
	1. countToken()   : 현재 남아 있는 token의 갯수를 int로 리턴
	2. hasMoreToken() : 남아 있는 token이 있는지 여부를 boolean으로 리턴
	3. nextToken()    : token을 하나씩 꺼내오는 메서드로 String으로 리턴
	   ... nextToken메서드는 더 이쌍 꺼내욜 token이 없을 경우에 NoSuchElementException
	       이 발생하기 때문에 예외처리를 해야 한다. 
*/
public class StringTokenizerMain {

	public static void main(String[] args) {
		
		String names = "홍길동/홍길순/홍길상/홍길준/홍길녀/홍길자"; // 구분자가 없을 경우 공란을 기준
		StringTokenizer st = new StringTokenizer(names, "/");
		System.out.println("현재 남아있는 토큰 수 = " + st.countTokens());
		System.out.println("토큰이 남아있는지 여부 = " + st.hasMoreTokens());
		System.out.println();
		
		String str = st.nextToken();
		System.out.println("꺼내온 token = " + str);
		System.out.println("현재 남아있는 토큰 수 = " + st.countTokens());
		System.out.println("토큰이 남아있는지 여부 = " + st.hasMoreTokens());
		System.out.println();
		
		str = st.nextToken();
		System.out.println("꺼내온 token = " + str);
		System.out.println("현재 남아있는 토큰 수 = " + st.countTokens());
		System.out.println("토큰이 남아있는지 여부 = " + st.hasMoreTokens());
		System.out.println();
		
		// 1. for
		st = new StringTokenizer(names, "/");
		int count = st.countTokens();
		System.out.println("현재 남아있는 토큰 수 = " + count);
		for(int i=0;i<count;i++) {
			String token = st.nextToken();
			System.out.print(token + ", ");
		}
		System.out.println("\n현재 남아있는 토큰 수 = " + st.countTokens());
		System.out.println("토큰이 남아있는지 여부 = " + st.hasMoreTokens());
		// st.nextToken(); // NoSuchElementException
		System.out.println();
		
		// 2. while
		st = new StringTokenizer(names, "/");
		while(st.hasMoreTokens()) {
			System.out.print(st.nextToken() + ", ");
		}
		System.out.println("\n현재 남아있는 토큰 수 = " + st.countTokens());
		System.out.println("토큰이 남아있는지 여부 = " + st.hasMoreTokens());		
	}

}

















