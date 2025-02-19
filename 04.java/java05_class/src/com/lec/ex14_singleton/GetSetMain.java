package com.lec.ex14_singleton;

public class GetSetMain {

	public static void main(String[] args) {
		
		GetSet singleton = new GetSet();
		System.out.println("num1 = " + singleton.num1);
		// System.out.println("num2 = " + singleton.num2);
		System.out.println("num2 = " + singleton.getNum2());
		System.out.println();
		
		singleton.num1 = 1000;
		System.out.println("num1 = " + singleton.num1);
		
		// singleton.num2 = 2000;
		singleton.setNum2(2000);
		System.out.println("num2 = " + singleton.getNum2());

		// 실습. name대한 속성을 읽거나 수정해보기
		singleton.setName("소향");
		 System.out.println("getName = " + singleton.getName());
		
		
	}

}
