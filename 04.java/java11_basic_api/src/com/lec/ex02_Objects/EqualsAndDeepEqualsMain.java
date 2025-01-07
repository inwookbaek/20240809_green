package com.lec.ex02_Objects;

import java.util.Objects;

/*
	Objects클래스

	2. 동등비교(equals(), deepEauals())
	
	   Objects.equals(Object a, Objecet b)메서드는 2 개객체의 동등비교를 하는데 
	   1) a와 b가 모두 null일 경우 true
	   2) a와 b가 모두 not null일 경우 a.equals(b)의 결과를 리턴
	   
	   Objects.deepEquals(Object a, Object b)메서드 역시 2 개객체의 동등을 비교하는데
	   1) a와 b가 서로 다른 배열일 경우, 항목의 값이 모두 같다면 true를 리턴
	   2) 이 메서드는 Arrays.deepEquals(Object[] a, Object[] b)와 동일한다.

*/
public class EqualsAndDeepEqualsMain {

	public static void main(String[] args) {

		int i0 = 1000;
		Integer i1 = 1000;
		Integer i2 = 1000;
		
		System.out.println("Objects.equals(i1, i2) = " + Objects.equals(i1, i2));
		System.out.println("Objects.equals(i1, null) = " + Objects.equals(i1, null));
		System.out.println("Objects.equals(null, i2) = " + Objects.equals(null, i2));
		System.out.println("Objects.equals(null, null) = " + Objects.equals(null, null));
		System.out.println();
		
		Integer[] o1 = {1,2,3};
		Integer[] o2 = {1,2,3};
		
		System.out.println("Objects.equals(o1, o2) = " + Objects.equals(o1, o2));  // false
		System.out.println("Objects.equals(o1, null) = " + Objects.equals(o1, null));
		System.out.println("Objects.equals(null, o2) = " + Objects.equals(null, o2));
		System.out.println("Objects.equals(null, null) = " + Objects.equals(null, null));
		System.out.println();
		
		System.out.println("Objects.deepEquals(o1, o2) = " + Objects.deepEquals(o1, o2)); // true
		System.out.println("Objects.deepEquals(o1, null) = " + Objects.deepEquals(o1, null));
		System.out.println("Objects.deepEquals(null, o2) = " + Objects.deepEquals(null, o2));
		System.out.println("Objects.deepEquals(null, null) = " + Objects.deepEquals(null, null));

	}

}
