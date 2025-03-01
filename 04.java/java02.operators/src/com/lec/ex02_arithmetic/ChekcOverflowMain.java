package com.lec.ex02_arithmetic;

public class ChekcOverflowMain {

	public static void main(String[] args) {
		
		System.out.println("int의 최소값 = " + Integer.MIN_VALUE + 
		           ", int의 최대값 = " + Integer.MAX_VALUE);

		// 문법에러는 아니나 산술(로직)에러
		System.out.println("2147483647 + 1 = " + (2147483647 + 1));
		
		try {
			int result = safeAdd(2147483647, 1);
			System.out.println("2147483647 + 1 = " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("int타입의 최소/최대값의 범위를 초과해서 연산을 할 수가 없습니다!");
		}
	}

	private static int safeAdd(int x, int y) {
		if(x>0) {
			if(x > (Integer.MAX_VALUE + y)) {
				// 연산을 하지않고 강제로 예외를 발생
				throw new ArithmeticException("int타입의 최대값을 초과했습니다!!");
			}
		} else {
			if(x > (Integer.MIN_VALUE - y)) {
				throw new ArithmeticException("int타입의 최소값을 초과했습니다!!");
			}			
		}
		return x + y;
	}
	
}






