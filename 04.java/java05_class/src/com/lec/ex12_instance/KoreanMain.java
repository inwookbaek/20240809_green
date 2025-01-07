package com.lec.ex12_instance;

public class KoreanMain {

	public static void main(String[] args) {
		
		Korean kim = new Korean("김갑순", "901225-1234567");
		System.out.println((kim.nation + ", " + kim.toString()));
		Korean hong = new Korean("홍길동", "970224-1234568");
		System.out.println((hong.nation + ", " + hong.toString()));
		System.out.println();
		
		kim.nation = "미국";
		System.out.println("국적 = " + Korean.nation);
		System.out.println((kim.nation + ", " + kim.toString()));
		System.out.println((hong.nation + ", " + hong.toString()));
		System.out.println();
		
		Korean.nation = "영국";
		System.out.println("국적 = " + Korean.nation);
		System.out.println((kim.nation + ", " + kim.toString()));
		System.out.println((hong.nation + ", " + hong.toString()));
	}

}
