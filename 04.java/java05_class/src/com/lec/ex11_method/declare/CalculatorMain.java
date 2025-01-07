package com.lec.ex11_method.declare;

public class CalculatorMain {

	public static void main(String[] args) {
		// Calculator객체를 생성하고 계산이 on/off, 4칙연산을 수행
		
		Calculator cal1 = new Calculator();
		cal1.powerOn();
		int result1 = cal1.add(100, 100);
		int result2 = cal1.sub(100, 100);
		int result3 = cal1.mul(100, 100);
		double result4 = cal1.div(100, 100);
		
		System.out.println("add = " + result1);
		System.out.println("sub = " + result2);
		System.out.println("mul = " + result3);
		System.out.println("div = " + result4);
		cal1.powerOff();
		System.out.println();
		
		String result5 = cal1.xxx(true);
		System.out.println("xxx = " + result5);
		
		String result6 = cal1.yyy();
		System.out.println("yyy = " + result6);
		System.out.println("yyy = " + result6.toString());
		System.out.println("yyy = " + result6.repeat(3));
		
		Calculator result7 = cal1.zzz();
		System.out.println("result7.add = " + result7.add(100, 1000));
		System.out.println("result7.sub = " + result7.sub(100, 1000));
		System.out.println("result7.mul = " + result7.mul(100, 1000));
		System.out.println("result7.div = " + result7.div(100, 1000));
		System.out.println();
		
		Calculator result8 = cal1.aaa();
		System.out.println("result8.add = " + result8.add(100, 1000));
		System.out.println("result8.sub = " + result8.sub(100, 1000));
		System.out.println("result8.mul = " + result8.mul(100, 1000));
		System.out.println("result8.div = " + result8.div(100, 1000));
	}

}
