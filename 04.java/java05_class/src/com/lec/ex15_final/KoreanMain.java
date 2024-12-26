package com.lec.ex15_final;

public class KoreanMain {

	public static void main(String[] args) {
				
		Korean kim = new Korean(3.14);
		// kim.pi = 1.00000; final 변경불가
		System.out.println("kim.pi = " + kim.pi);
		// kim.pi1 = 1.1111; // 변경불가
		System.out.println("kim.pi1 = " + kim.pi1);
		System.out.println("kim.PI = " + kim.PI);
		System.out.println();
		
		Korean hong = new Korean(3.141592);
		System.out.println("hong.pi = " + hong.pi);	
		System.out.println("hong.pi1 = " + hong.pi1);
		System.out.println("hong.PI = " + hong.PI);
		System.out.println();

		Korean kor1 = new Korean("홍길동", "991111-1234567");
		Korean kor2 = new Korean("홍길숨", "901111-2234567");
		System.out.println("kor1 = " + kor1.toString());
		System.out.println("kor2 = " + kor2.toString());
	}

}
