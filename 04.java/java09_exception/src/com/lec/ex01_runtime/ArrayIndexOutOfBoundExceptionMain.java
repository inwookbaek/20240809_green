package com.lec.ex01_runtime;

public class ArrayIndexOutOfBoundExceptionMain {

	public static void main(String[] args) {
		
		// java.lang.ArrayIndexOutOfBoundsException
		String data1 = args[0];
		String data2 = args[1];
		
		System.out.println("data1 = args[0] : " + data1);
		System.out.println("data2 = args[1] : " + data2);
		System.out.println("args[1] : " + args[1]);
	}

}
