package com.lec.ex05_String.basic;

import java.io.UnsupportedEncodingException;

public class StringMethodMain2 {

	public static void main(String[] args) {
		// 6. charAt
		System.out.println("6. charAt()");
		String ssn = "991118-1234567";		
		System.out.println(String.format("... ssn.charAt() = %s", ssn.charAt(7)));
		System.out.println(String.format("... ssn.charAt() = %s", (ssn.charAt(7) == '1' ? "남자" : "여자")));
		System.out.println();
		
		// 7. getBytes
		// a. getBytes()
		System.out.println("7. getBytes()");
		String str = "안녕하세요";
		System.out.println(String.format("... str.getBytes().length = %d bytes", str.getBytes().length));
		System.out.println(String.format("... new String(str.getBytes()) = %s", new String(str.getBytes())));
		System.out.println();
		
		// b. getBytes(문자셋)
		try {
			byte[] byte1 = str.getBytes("EUC-KR");
			System.out.println("... EUC-KR : byte1.length " + byte1.length);
			byte1 = str.getBytes("UTF-8");
			System.out.println("... UTF-8 : byte1.length " + byte1.length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
		// 8. indexOf
		// 매개값으로 주어진 문자열이 시작되는 위치(인덱스)를 정수로 리턴
		// 만약, 주어진 문자열을 찾지 못했을 경우 -1을 리턴
		System.out.println("8. indexOf");
		String 제목 = "매개값으로 주어진 문자열이 시작되는 위치(인덱스)를 정수로 리턴";
		System.out.println("... 제목에서 '문자열'의 위치는 = " + 제목.indexOf("문자열"));
		System.out.println("... 제목에서 '이강인'의 위치는 = " + 제목.indexOf("이강인"));
		
		String 뉴스 = new String(제목);
		if(뉴스.indexOf("정몽규") >= 0) {
			System.out.println("... 정몽규축협회장의 뉴스기사를 검색했습니다!!");
		} else {
			System.out.println("... 정몽규축협회장의 뉴스기사를 검색하지 못했습니다!!");			
		}
		
		// 9. startsWith
		System.out.println("9. startsWith");
		String title = "Hello World!!";
		System.out.println(String.format("... title.startsWith('World') = %s", title.startsWith("World")));
		System.out.println(String.format("... title.startsWith('Hello') = %s", title.startsWith("Hello")));
		System.out.println(String.format("... title.startsWith('H') = %s", title.startsWith("H")));
		System.out.println(String.format("... title.startsWith('Hello W') = %s", title.startsWith("Hello W")));
		System.out.println();
		
		// 10. endsWith
		System.out.println("10. endsWith");
		System.out.println(String.format("... title.endsWith('World!!') = %s", title.endsWith("World!!")));
		System.out.println(String.format("... title.endsWith('Hello') = %s", title.endsWith("Hello")));
		System.out.println(String.format("... title.endsWith('H') = %s", title.endsWith("H")));
		System.out.println(String.format("... title.endsWith('Hello W') = %s", title.endsWith("Hello W")));
		System.out.println();
		
		// 11. contains
		System.out.println("11. contains");
		System.out.println(String.format("... title.contains('World') = %s", title.contains("World")));
		System.out.println(String.format("... title.contains('Hello') = %s", title.contains("Hello")));
		System.out.println(String.format("... title.contains('H') = %s", title.contains("H")));
		System.out.println(String.format("... title.contains('Hello W') = %s", title.contains("Hello W")));
	}
}




















