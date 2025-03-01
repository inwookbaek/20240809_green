package com.lec.ex03_for;

public class ForMain {

	public static void main(String[] args) {
		
		// i는 for문안에서 정의된 지역변수 즉, for블럭안에서만 사용가능한 변수	
		for(int i=1;i<=10;i++) {
			System.out.print(i + ((i<10) ? "," : ""));
		}
		// System.out.println("i = " + i); // 에러 : 지역변수
		System.out.println();

		// 1~100까지 합
		int sum = 0;
		int i = 0;   // 전역변수
		
		for(i=1;i<=100;i++) {
			sum += i;
		}
		System.out.println("1~100까지 합 = " + sum);
		System.out.println(String.format("1~100까지 합 = %s", sum));
		System.out.println("i = " + i);
	}

}
