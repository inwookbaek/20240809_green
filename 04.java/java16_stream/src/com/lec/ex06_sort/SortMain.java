package com.lec.ex06_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SortMain {

	public static void main(String[] args) {
		// 1. 기본타입
		IntStream stream = Arrays.stream(new int[] {6,3,1,5,2,4});
		stream.forEach(System.out :: print);
		System.out.println();
		
		stream = Arrays.stream(new int[] {6,3,1,5,2,4});
		stream.sorted().forEach(System.out :: print);
		System.out.println();
		
		// 2. 참조타입 - Member의 회원번호와 이름순으로 정렬
		List<Member> members = Arrays.asList(
				new Member(3, "홍길동"),
				new Member(1, "홍길순"),
				new Member(2, "홍길녀")
			);
		
		members.stream().forEach(m -> {System.out.println(m.toString());});
		System.out.println();
		
		members.stream().forEach(m -> System.out.println(m.toString()));
		System.out.println();

		members.stream().forEach(System.out :: println);
		System.out.println();
		
		// 오름차순
		members.stream().sorted().forEach(System.out :: println);
		System.out.println();
		
		// 내림차순
		members.stream().sorted(Comparator.reverseOrder()).forEach(System.out :: println);

	}
}

class Member implements Comparable<Member> {
	
	private int no;
	private String name;
	
	public Member(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Member [no=" + no + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Member m) {
		// 번호순 
		// return Integer.compare(this.no, m.no); // -1 0 1
		// 이름순
		return this.name.compareTo(m.name);
	}
}




















