package com.lec.ex03_for;

public class ForNestedMain {

	public static void main(String[] args) {
		// 실습. 구구단 출력해 보기
		
		for(int dan=2;dan<=9;dan++) {
			System.out.println("----" + dan + "단 ----");
			for(int i=2;i<=9;i++) {
				System.out.println(dan + " x " + i + " = " + (dan*i));
			}
		}
	}

}
