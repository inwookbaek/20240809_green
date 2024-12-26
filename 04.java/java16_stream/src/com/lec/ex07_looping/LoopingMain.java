package com.lec.ex07_looping;

import java.util.Arrays;

/*
	루핑(중간처리 peek(), 최동처리 forEach())
	
	루핑(Looping)은 요소 전체를 반복하는 것이다. 루핑메서드는 peek()와 forEach()가 있는데
	이 두 메서드는 반복처리하는 기능은 동일하지만 peek()는 중간처리메서드이고 forEach()는
	최종처리메서드이다.
	
	peek()는 중간터리단계에서 전체요소를 루핑하면서 추가적인 작업을 하기 위해 사용되며 최종
	처리메서드가 실행되지 않으면 지연(lazy, 처리가되지 않는다는의미)이 되기 때문에 반드시
	최종처리메서드가 호출되어야만 동작한다.
	
	요소처리의 최종단계가 합계를 구하는 것이라면 peek()호출후 sum()을 호출해야만 peek()가
	정상적으로 동작한다.
	
	하지만, forEach()는 최종처리메서드이기 때문에 파이프라인 마지막에서 루핑하면서 요소를
	하나씩 처리한다. forEach()는 최종처리이기 때문에 다른 최종처리인 sum()과 같은 최종처리
	메서드를 호출하면 에러가 발생한다.
*/
public class LoopingMain {

	public static void main(String[] args) {
		
		int[] int_array = {1,2,3,4,5,6,7,8,9,10};
		
		// 실습. 짝수의 합계를 stream을 사용해서 구하기
		// filter().sum();
		int result = Arrays.stream(int_array).filter(n -> n%2==0).sum();
		System.out.println("1~10까지의 짝수 합 = " + result);
		
		// 1. peek
		// peek는 중간처리이기 때문에 최종처리가 호출되지 않으면 실행되지 않음
		System.out.println("[1. peek()를 마지막에 호출하는 경우!!]");
		Arrays.stream(int_array)
			.filter(n -> n%2==0)
			.peek(n -> System.out.print(n + ", "));  
		System.out.println();
		
		System.out.println("[2. 최종처리(sum)를 마지막에 호출하는 경우!!]");
		result = Arrays.stream(int_array)
					.filter(n -> n%2==0)
					.peek(n -> System.out.print(n + ", "))
					.sum();
		System.out.println("\n1~10까지의 짝수 합 = " + result);
		System.out.println();
		
		System.out.println("[3. 최종처리(forEach)를 마지막에 호출하는 경우!!]");
		Arrays.stream(int_array)
		.filter(n -> n%2==0)
		// .sum() : 최종처리인 sum과 forEach가 중복선언, 최종처리는 하나만 선언 되야 한다.
		.forEach(n -> System.out.print(n + ", "));
		
		
	}

}


















