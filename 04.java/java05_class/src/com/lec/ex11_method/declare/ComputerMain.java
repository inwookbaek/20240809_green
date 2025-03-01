package com.lec.ex11_method.declare;

public class ComputerMain {

	public static void main(String[] args) {
		
		Computer com1 = new Computer();
		int result1 = com1.add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println("result1 = " + result1);
		System.out.println("result2 = " + com1.add(1, 2, 3, 4, 5));
		System.out.println("result3 = " + com1.add(1, 2, 3));
		
		int result4 = com1.add(new int[] {1,2,3,4,5,6,7,8,9,10});
		System.out.println("result4 = " + result4);

		int result5 = com1.add1(1,2,3,4,5,6,7,8,9,10);
		System.out.println("result5 = " + result5);
		System.out.println("result5 = " + com1.add1(1,2,3,4,5));
		System.out.println("result5 = " + com1.add1(1,2,3));
	}

}
