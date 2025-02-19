package com.lec.ex02_try_catch;

public class TryCatchMain2 {

	public static void main(String[] args) {
		// 2. 실행예외
		// java.lang.ArrayIndexOutOfBoundsException
		String data1 = null;
		String data2 = null;
		
		try {
			data1 = args[0];
			data2 = args[1];
			System.out.println("agrs.length = " + args.length);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("agrs.length = " + args.length + ", 실행매개변수가 부족합니다!!");
			System.out.println("예외클래스이름 = " + e.getClass().getName());
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
		System.out.println();
		
		data1 = "100a";
		
		System.out.println(data1 + ", " + data2);
		
		try {
			int val1 = Integer.parseInt(data1);
			double val2 = Double.parseDouble(data2);
			double result = val1 + val2;
			System.out.println("val1 + val2 = " + val1 + val2);
		} catch (NumberFormatException e) {
			System.out.println(data1 + " or " + data2 + "는 숫자로 변환할 수 없습니다!!");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		System.out.println("아주 아주 중요한 로직을 실행!!");
	}

}
