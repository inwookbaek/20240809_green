package com.lec.ex03_pipeline;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

/*
	스트림 파이프라인?
	
	대량의 데이터를 가공해서 축소하는 것을 일반적으로 리덕션(Reduction)이라고 하는데, 데이터의 합계,
	평균, 카운팅, 최대/최소값등이 대표적인 reduction의 결과이다. 그러나 컬렉션의 요소를 리덕션의 결
	과물로 바로 집계할 수 없을 경우에는 집계하기 편리하도록 필터링, 매핑, 그룹핑등의 중간처리를 하는
	것이 필요하다.
	
	1. 중간처리와 최종처리
		
	   스트림은 데이터의 필터링, 매핑, 정렬, 그룹핑등의 중간처리와 합계, 평균등의 최종처리를 파이프
	   라인(Pipe Line)으로해결한다. 파이프라인은 여러개의 스트림이 연결되어 있는 구조를 말한다.
	   
	   중간스트림이 생성될 떄 요소들이 바로 중간처리되는 것이 아니라 최종처리가 시작되기 전까지는 
	   중간처리가 지연(lazy)된다. 최종처리가 시작되면 비로소 컬렉션의 요소가 중간 스트림에서 처리
	   되고 최종처리까지 처리하게 된다.		
		
	2. 중간처리와 최종처리 메서드
	
	   1) 중간처리메서드
	   
	      a. 필터링: distinct(), filter()
	      b. 매핑  : flatMap(), flatMapToDouble(), flatMapToLong(), flatMapToInt(),
	                 map(), mapToDouble(), mapToLong, mapToInt(),
	                 asDoubleStream(), asLongStream(), boxed()
	      c. 정렬  : sorted()
	      d. 루핑  : peek()
	   
	   2) 최종처리메서드
	   
	      a. 매칭 : allMatch(), anyMatch(), noneMatch()
	      b. 집계 : count(), findFirst(), max(), min(), average(), sum(), reduce()
	      c. 루핑 : forEach()
	      d. 수집 : collect() 	
*/
public class PipeLineMain {

	public static void main(String[] args) {
		
		List<Member> members = Arrays.asList(
			new Member("홍길동", Member.MALE, 30),
			new Member("홍길순", Member.FEMALE, 34),
			new Member("홍길녀", Member.FEMALE, 32),
			new Member("홍길상", Member.MALE, 28),
			new Member("홍길성", Member.MALE, 35)
		);
		
		// 남자회원의 평균나이는?
		// 1. 일반로직
		double sum = 0.0;
		for(Member member:members) {
			if(member.getGender() == Member.MALE) {
				sum += member.getAge();
			}
		}
		System.out.println("[일반] 남자회원의 평균나이 = " + (sum/(members.size()-2)));
		
		// 2. stream로직(pipeline)
		OptionalDouble avgAge = members.stream()
				                       .filter(m -> m.getGender() == Member.MALE) // 중간처리 - 남자회원만
				                       // .mapToInt(m -> m.getAge())              // 중간처리 - 나이만
				                       .mapToInt(Member :: getAge)                   
				                       .average();                                // 최종처리
		System.out.println("[Stream] 남자회원의 평균나이 = " + avgAge.getAsDouble());
		
		double 평균나이 = members.stream()
				.filter(m -> m.getGender() == Member.MALE) // 남자회원만
				// .mapToInt(m -> m.getAge())              // 나이만
				.mapToInt(Member :: getAge)                   
				.average()
				.getAsDouble();
		System.out.println("[getAsDouble] 남자회원의 평균나이 = " + 평균나이);
		
		// 여자회원의 평균나이는?
		평균나이 = members.stream()
				.filter(m -> m.getGender() == Member.FEMALE) // 남자회원만
				// .mapToInt(m -> m.getAge())              // 나이만
				.mapToInt(Member :: getAge)                   
				.average()
				.getAsDouble();
		System.out.println("[getAsDouble] 여자회원의 평균나이 = " + 평균나이);		
		
	}

}

class Member {
	
	public static final int MALE = 0;
	public static final int FEMALE = 1;
	
	private String name;
	private int gender;
	private int age;
	public Member(String name, int gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
