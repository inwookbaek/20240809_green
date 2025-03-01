package com.lec.ex07_auxstream;

import java.util.Date;

/*
	printf() 메서드
	
	PrintStream과 PritWriter에는 print(), println()메서드이외에 printf()메서드가 있다.
	이 메서드는 형식화된 문자열을 출력할 수 있도록 Java8에서 부터 추가된 메서드이다.
	
	형식화문자열
	
	1. 정수     : %d(정수, 123), %6d(6자리정수 왼쪽은 공백 ___123), %-6d(오른쪽공백 123___), %06d(000123)
	2. 실수     : %10.2f(_____123.45), %-10.2f(123.45_____), %010.2f(00000123,45)
	3. 문자열   : %s(abc), %6s(___abc), %-6s(abc___)
	4. 날짜     : %tF(%tY-%tm-%td 2023-05-01), %tY(yyyy), %ty(yy), %tH(0~23), %tl(0~12), %tM(분), %ts(초)
	5. 특수문자 : /t, /n, /r, /%(%)
*/
public class PrintFormatMain {

	public static void main(String[] args) {
		// 1. 정수
		System.out.printf("상품가격 : %d원 입니다! \n", 1000);
		System.out.printf("상품가격 : %6d원 입니다! \n", 1000);
		System.out.printf("상품가격 : %-6d원 입니다! \n", 1000);
		System.out.printf("상품가격 : %06d원 입니다! \n", 1000);
		System.out.println();
		
		// 2. 실수
		System.out.printf("반지름이 %d인 원의 넓이는 %10.2f입니다! \n", 10, Math.PI*10*10);
		System.out.println();
		
		// 3. 문자열..
		System.out.printf("%6d|%-10s|%10s\n", 1,"홍길동","가수");
		System.out.println();
		
		// 4. 날짜
		Date now = new Date();
		System.out.printf("오늘은 %tY년 %tm월 %td일 입니다!\n", now, now, now);
		System.out.printf("오늘은 %1$tY년 %2$tm월 %3$td일 입니다!\n", now, now, now);
		System.out.printf("오늘은 %1$tY년 %1$tm월 %1$td일 입니다!\n", now);
		System.out.printf("현재시간은 %1$tH시 %1$tM분 %1$tS초 입니다!\n", now);
	}
}