package com.lec.ex05_String.basic;

public class StringMethodMain1 {

	public static void main(String[] args) {
		// 1. replace
		String oldStr = "자바 프로그램";
		String newStr = oldStr.replace("자바", "파이썬");
		System.out.println(String.format("1. replace()\n... %s -> %s", oldStr, newStr));
		System.out.println(String.format(" ... hashCode : %d -> %d", oldStr.hashCode(), newStr.hashCode()));
		
		oldStr = oldStr.replace("자바", "Java");
		System.out.println(String.format(" ... Old String : %s -> %s", oldStr, oldStr));
		System.out.println(String.format(" ... hashCode : %d -> %d", oldStr.hashCode(), oldStr.hashCode()));
		System.out.println();
		
		// 2. substring
		// a. substring(int from, int to) : to-1
		// b. substring(int from)
		System.out.println("2. substring()");
		String ssn = "991118-1234567";
		String birthDay = ssn.substring(0, 6);
		System.out.println("... 생년월일 = " + birthDay);
		System.out.println("... 주민번호 = " + ssn.substring(7));
		System.out.println("... 성별 = " + (ssn.substring(7, 8).equals("1") ? "남자" : "여자"));
		System.out.println();
		
		// 3. toLowerCase / toUpperCase
		System.out.println("3. toLowerCase / toUpperCase");
		System.out.println("3. toLowerCase / toUpperCase");
		System.out.println(String.format("... toUpperCase(Java Program) = %s", "Java Program".toUpperCase()));
		System.out.println();
		
		// 4. trim
		System.out.println("4. trim");
		System.out.println(String.format("... trim(  Java Program  ) = %s", "  Java Program  ".trim()));
		System.out.println(String.format("... trim(  02) = %s", "  02".trim()));
		System.out.println(String.format("... trim(02  ) = %s", "02  ".trim()));
		System.out.println(String.format("... trim( 02 ) = %s", " 02 ".trim()));
		System.out.println(String.format("... trim(0  2) = %s", "0  2".trim()));
		System.out.println();
		
		// 5. valueOf : int or double타입등을 String으로 변경
		System.out.println("5. valueOf");
		System.out.println(String.format("... String.valueOf(true) = %s", String.valueOf(true)));
		System.out.println(String.format("... String.valueOf(false) = %s", String.valueOf(false)));
		System.out.println(String.format("... String.valueOf(1000) = %s", String.valueOf(1000)));
		System.out.println(String.format("... String.valueOf(3.14) = %s", String.valueOf(3.14)));
		System.out.println(String.format("... String.valueOf(1000) + String.valueOf(3.14) = %s", String.valueOf(1000) + String.valueOf(3.14)));
		System.out.println();
		
		System.out.println(String.format("... Integer.parseInt(\"1000\") + Integer.valueOf(\"314\") = %d", Integer.parseInt("1000") + Integer.valueOf("314")));
	}

}
