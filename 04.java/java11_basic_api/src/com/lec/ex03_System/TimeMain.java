package com.lec.ex03_System;

/*	   
	4. 현재시간(currentTimeMills(), nanoTime())
	
	   Systmem클래스의 currentTimeMills, nanoTime메서드는 컴퓨터시계로 부터 현재시간을 읽어서 밀리
	   세컨드(1/1000), 나노세컨트(1/10^9)단위의 long타입의 값을 리턴한다. 이 메서드는 주로 프로그램
	   의 소요시간을 측정하는데 사용된다.	
*/
public class TimeMain {

	public static void main(String[] args) {
		// 1~1000000까지의 합을 구하는데 소요된 시간은?
		long start = System.nanoTime();
		
		int sum = 0;
		for(int i=1;i<=1000000;i++) {
			sum += i;
		}
		
		long end = System.nanoTime();
		System.out.println("1~1000000까지의 합 = " + sum);
		System.out.println("1~1000000까지의 합의 소요시간 = " + (end - start) + " ns");
		System.out.println();
		
		start = System.currentTimeMillis();
		
		sum = 0;
		for(int i=1;i<=1000000;i++) {
			sum += i;
		}
		
		end = System.currentTimeMillis();
		System.out.println("1~1000000까지의 합 = " + sum);
		System.out.println("1~1000000까지의 합의 소요시간 = " + (end - start) + " ms");		
		
	}

}
