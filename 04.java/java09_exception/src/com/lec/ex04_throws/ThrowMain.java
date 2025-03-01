package com.lec.ex04_throws;

public class ThrowMain {

	public static void main(String[] args) {
		// 강제로 예외를 발생시키기
		try {
			// throw new ClassCastException();
			// throw new ClassNotFoundException();
			// throw new NumberFormatException();
			throw new ArrayIndexOutOfBoundsException();
		} catch (Exception e) {
			System.out.println("강제로 예외를 발생시켰습니다!!! " + e.getClass().getName());
			e.printStackTrace();
		}

	}

}
