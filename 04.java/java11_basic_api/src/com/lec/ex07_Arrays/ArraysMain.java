package com.lec.ex07_Arrays;

import java.util.Arrays;

/*
	Arrays클래스
	
	Arrays클래스는 배열에 관한 조작기능을 지원하는 유틸리티클래스이다. 배열의 복사, 요소의 정렬
	요소검색과 같은 기능을 제공한다.
	
	간단한 배열의 복사는 System.arraycopy()메서드를 사용할 수 있으나 Arrays는 추가적으로 요소정렬
	검색, 비교와 같은 기능을 제공한다. Arrays관련 모든 메서드는 static메서드이기 때문에 객체생성
	없이 바로 Arrays클래스로 직접 메서드를 호출할 수 있다.
*/
public class ArraysMain {

	public static void main(String[] args) {
		
		// 1. 기본타입정렬
		// Arrays.sort() - 기본 오름차순으로 정렬
		int[] scores = {88,77,99};
		Arrays.sort(scores);
		for(int score:scores) {
			System.out.print(score + ", ");
		}
		System.out.println();
		
		// 2. 검색 - binarySearch()메서드를 이용, 리턴은 요소위치
		int index = Arrays.binarySearch(scores, 88) + 1;
		System.out.println("88점의 위치 = " + index);
		System.out.println();
		
		// 3. 참조타입정렬
		String[] names = {"손흥민","이강인", "홍길동", "김민재", "소향"};
		Arrays.sort(names);
		for(String name:names) {
			System.out.print(name + ", ");
		}
		System.out.println();
		index = Arrays.binarySearch(names, "소향") + 1;
		System.out.println("소향의 위치 = " + index);
		System.out.println();
		
		// 4. 객체의 정렬과 비교
		// 객체를 정렬하려면 Comparable인터페이스의 compareTo(객체)를
		// 구현한 객체만 정렬, 비교할 수 있다. 만약에, Comparable를
		// 구현하지 않고 정렬을 할 경우에는 예외가 발생된다.
		Member m1 = new Member("손흥민");
		Member m2 = new Member("이강인");
		Member m3 = new Member("홍길동");
		Member m4 = new Member("김민재");
		Member m5 = new Member("소향");
	
		Member[] members = {m1, m2, m3, m4, m5, new Member("황희찬")};
		System.out.println("members.length = " + members.length);
		Arrays.sort(members); // public static void sort(Object[] a), 강제형변환 예러
		for(Member member:members) {
			System.out.print(member.name + ", ");
		}
		System.out.println();
		 index = Arrays.binarySearch(members, new Member("소향")) + 1;
		 System.out.println("소향의 위치 = " + index);
		
		System.out.println(Arrays.toString(members));
	}	
}

class Member implements Comparable<Member> {
	
	String name;
	
	public Member(String name) {
		super();
		this.name = name;
	}

	@Override
	public int compareTo(Member m) {
		return this.name.compareTo(m.name); // -1 0 1
	}	
	
	@Override
	public String toString() {
		return this.name;
	}
}








