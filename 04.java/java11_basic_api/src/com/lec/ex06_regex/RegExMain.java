package com.lec.ex06_regex;

import java.util.regex.Pattern;

/*
	정규표현식과 Pattern 클래스(java.util.regex.Pattern)
	
	문자열에 정해져인는 형식(정규식, Regular Expression)으로 구성이 되어 있는지 여부를 검증
	할 때 사용한다.(정규식을 작성하는 방법은 API에서 java.util.regex.Pattern클래스를 참조)
	
	간단히 말해서 정규식은 문자 or 숫자등 반복기호가 결합된 문자열이다. 예를 들어서 문자열이
	02-123-1234 또는 010-1234-1234와 같이 전화번호를 포함하는지 여부를 확이할 수가 있는데
	정규식으로 표현하면 "(02|010)-\d{3,4}-\d{4}"의 형태인지 여부를 boolean으로 리턴, 이메일일
	경우 "hong@gmail.com"일 패턴은 "\w+@\w+\.\w(\.\w+)?"의 형태로 검증할 수가 있다.
*/
public class RegExMain {

	public static void main(String[] args) {
		
		// 1. 전화번호유효성검증
		String tel1 = "02-123-4567";
		String tel2 = "010-1234-4567";
		String tel3 = "051-1234-4567";
		
		// 검증조건
		// 1) 지역번호가 2, 3자리인지? 숫자인지?
		// 2) 전화번호의 구분자가 "-"인지?
		// 3) 국번이 3,4자리인지? 숫자인지?
		// 4) 뒤자리가 4자리인지? 숫자인지?
		// 정규식패턴
		String re = "(02|010|031|051)-\\d{3,4}-\\d{4}";
		boolean result = Pattern.matches(re, tel1);
		System.out.println(tel1 + " : 정규식패턴일치여부 = " + result);
		System.out.println(tel2 + " : 정규식패턴일치여부 = " + Pattern.matches(re, tel2));
		System.out.println(tel3 + " : 정규식패턴일치여부 = " + Pattern.matches(re, tel3));
		System.out.println();
		
		System.out.println("정규식패턴일치여부 = " + Pattern.matches(re, "010-123a-1234"));
		System.out.println("정규식패턴일치여부 = " + Pattern.matches(re, "010-1234-12345"));
		System.out.println("정규식패턴일치여부 = " + Pattern.matches(re, "010-1234-123"));
		System.out.println();
		
		if(result) {
			System.out.println("전화번호입력이 성공했습니다!");
		} else {
			System.out.println("전화번호형식 틀립니다! 다시 입력하세요!");
		}
		
		if(Pattern.matches(re, "032-123-1234")) {
			System.out.println("전화번호입력이 성공했습니다!");
		} else {
			System.out.println("전화번호형식 틀립니다! 다시 입력하세요!");
		}
		System.out.println();
		
		// 2. eMail유효성검증
		String email = "admin@gmail.com";
		re = "\\w+\\@\\w+(\\.\\w+)?";
		
		if(Pattern.matches(re, email)) {
			System.out.println("이메일입력이 성공했습니다!");
		} else {
			System.out.println("이메일형식 틀립니다! 다시 입력하세요!");
		}
		
		if(Pattern.matches(re, "admin@gmail")) {
			System.out.println("이메일입력이 성공했습니다!");
		} else {
			System.out.println("이메일형식 틀립니다! 다시 입력하세요!");
		}
		
		re = "^[a-zA-Z0-9_+&*-]+(?:\\." +
		        "[a-zA-Z0-9_+&*-]+)*@" +
		        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
		        "A-Z]{2,7}$";
		
		if(Pattern.matches(re, "admin@gmail")) {
			System.out.println("이메일입력이 성공했습니다!");
		} else {
			System.out.println("이메일형식 틀립니다! 다시 입력하세요!");
		}
		System.out.println();
		
		// 3. 문자검증
		System.out.println("[a-z], a -> " + Pattern.matches("[a-z]", "a")); // 소문자
		System.out.println("[a-z], A -> " + Pattern.matches("[a-z]", "A")); // 소문자
		System.out.println("[A-Z], a -> " + Pattern.matches("[A-Z]", "a")); // 대문자
		System.out.println("[A-Z], A -> " + Pattern.matches("[A-Z]", "A")); // 대문자
		System.out.println("[a-zA-Z], a -> " + Pattern.matches("[a-zA-Z]", "a")); // 대소문자
		System.out.println("[a-zA-Z], A -> " + Pattern.matches("[a-zA-Z]", "A")); // 대소문자
		System.out.println("[a-zA-Z], AaXyz -> " + Pattern.matches("[a-zA-Z]", "AaXyz")); // +단어단위, 대소문자
		System.out.println("[a-zA-Z]+, AaXyz -> " + Pattern.matches("[a-zA-Z]+", "AaXyz")); // +단어단위, 대소문자
		System.out.println();
			
		// 4. 숫자검증
		System.out.println("[0-9], 9 -> " + Pattern.matches("[0-9]", "9")); // 숫자
		System.out.println("[0-9], 9000 -> " + Pattern.matches("[0-9]", "9000")); // 개별, 숫자
		System.out.println("[0-9]+, 9000 -> " + Pattern.matches("[0-9]+", "9000")); // 단어, 숫자
		System.out.println();
		
		// 5. 영문자(대소), _, 숫자
		System.out.println("[0-9a-zA-Z]+, 01a8b -> " + Pattern.matches("[0-9a-zA-Z]+", "01a8b"));
		System.out.println("[0-9a-zA-Z]+, 01a8b -> " + Pattern.matches("[0-9a-zA-Z]+", "01a8b_"));
		System.out.println("[0-9a-zA-Z_]+, 01a8b -> " + Pattern.matches("[0-9a-zA-Z_]+", "01a8b_"));
		System.out.println();
		
		// 6. [0-9a-zA-Z_]+ = \w+ 
		//    [^0-9a-zA-Z_]+ = \W+
		System.out.println("\\w+, 01a8bA_ -> " + Pattern.matches("\\w+", "01a8bA_"));
		System.out.println("\\W+, 01a8bA_ -> " + Pattern.matches("\\W+", "01a8bA_"));
		System.out.println("[^0-9a-zA-Z_]+, 01a8bA_ -> " + Pattern.matches("[^0-9a-zA-Z_]+", "01a8bA_"));
		System.out.println("[^0-9a-zA-Z_]+,  @$* -> " + Pattern.matches("[^0-9a-zA-Z_]+", " @$*"));
		System.out.println("\\W+, @$* -> " + Pattern.matches("\\W+", "@$*"));

	}
}
