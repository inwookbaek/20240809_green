package com.lec.ex09_aggregate;

import java.util.Arrays;
import java.util.OptionalDouble;

/*
	집계(count(), sum(), average(), max() min90..)
	
	집계(aggregate)는 최종처리기능으로 요소들을 처리해서 갯수, 합계, 평균, 최대/최소값등과 같이
	하나의 값으로 집계하는 기능을 말한다.
	
	집계는 대용량의 데이터를 가공해서 축소하는 reduction이라고 볼 수 있다. 스트림이 제공하는
	기본집계 메서드는
	
	1. count() : 요소의 갯수를 long타입으로 리턴
	2. findFirst() : 첫 번째요소를 OptionalXXX타입으로 리턴
	3. max(Comparator<T>) : 요소중 최대요소를 OptionalXXX 타입으로 리턴
	4. min(Comparator<T>) : 요소중 최소요소를 OptionalXXX 타입으로 리턴
	5. average() : 요소들의 평균값을 OptionalDouble타입으로 리턴
	6. sum() : 요소들의 합계를 int, long, double중 한 개의 타입으로 리턴
	
	이 집계요소에서 리턴하는 OptionalXXX클래스는 Java8에서 추가한 java.util 패키지의 Optional,
	OptionalInt, OptionalLong, OptionalDouble클래스 타입을 말한다. 이들 객체에서 값을 얻기 위
	해서는 각각 get(), getAsInt(), getAsLong(), getAsDouble()메서드를 호출하면 된다.
	
	이 OptionalXXX클래스들은 저장하는 값의 타입만 다를 뿐 제공하는 기능은 거의 유사하다.
	Optional클래스는 단순히 집계만 제공하는 것이 아니라 집계값이 존재하지 않을 경우 디폴트값을
	설정할 수 있고 집계값을 처리하는 Comsumer 클래스도 등록할 수 있다.

*/
public class AggregateMain {

	public static void main(String[] args) {
		
		int[] int_arr = {1,8,3,4,5,6,7,2,9,10};
		
		// 1. count
		long count = Arrays.stream(int_arr).filter(n -> n%2==0).count();
		System.out.println("[count] 2의 배수의 개수 = " + count);
				
		// 2. sum
		int sum = Arrays.stream(int_arr).filter(n -> n%2==0).sum();
		System.out.println("[sum] 2의 배수의 합계 = " + sum);
		System.out.println();
		
		// 3. average
		OptionalDouble avg = Arrays.stream(int_arr).filter(n -> n%2==0).average();
		System.out.println(avg.getClass());
		System.out.println("[average().getAsDouble()] 2의 배수의 평균 = " + avg.getAsDouble());
		System.out.println("[average().getAsDouble()] 2의 배수의 평균 = " 
				+ Arrays.stream(int_arr).filter(n -> n%2==0).average().getAsDouble());
		double result2 = Arrays.stream(int_arr).filter(n -> n%2==0).average().getAsDouble();
		System.out.println("[average().getAsDouble()] 2의 배수의 평균 = " + result2);
		System.out.println();
		
		// 4. max
		System.out.println(Arrays.stream(int_arr).filter(n -> n%2==0).max().getClass());
		System.out.println("[max()] 2의 배수의 최대값 = " + 
				Arrays.stream(int_arr).filter(n -> n%2==0).max().getAsInt());
		System.out.println();
		
		// 5. min
		System.out.println(Arrays.stream(int_arr).filter(n -> n%2==0).min().getClass());
		System.out.println("[max()] 2의 배수의 최소값 = " + 
				Arrays.stream(int_arr).filter(n -> n%2==0).min().getAsInt());
		System.out.println();

		// 6. findFirst
		System.out.println(Arrays.stream(int_arr).filter(n -> n%2==0).findFirst().getClass());
		System.out.println("[max()] 2의 배수중 첫 번째값 = " + 
				Arrays.stream(int_arr).filter(n -> n%2==0).findFirst().getAsInt());
		System.out.println();

	}

}

















