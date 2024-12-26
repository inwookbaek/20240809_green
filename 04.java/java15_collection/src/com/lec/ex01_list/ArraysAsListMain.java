package com.lec.ex01_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysAsListMain {

	public static void main(String[] args) {
		// 1. 일반배열의 초기화
		String[] names = {"홍길동","손흥민","소향","이강인","김민재","홍길순","홍길상"};
		
		// ArrayList에는 초기화 기능이 없다. 대신에 Arrays.asList(값....값)메서드를 이용해서
		// 초기화시킬 수가 있다. ArrayList는 객체를 생성하고 필요에 따라서 값을 추가하는 방법이
		// 일반적이지만 고정된 객체를 생성할 경우에는 Arrays.asList()메서드를 이용하면 편리
		// 하다. 다만 Arrays.asList()메서드로 생성된 ArrayList객체는 크기를 변경할 수 없다.
		List<String> list1 = new ArrayList<String>(6);
		list1.add("추가가능");
		list1.remove(0);  // 삭제가능
		System.out.println("list1의 갯수 = " + list1.size());
		
		// ArrayList()생성자를 이용하는 대신에 Arrays.asList로 간편하게 생성할 수 있다.
		List<String> list2 = Arrays.asList("홍길동","손흥민","소향","이강인","김민재","홍길순","홍길상");
		// list2.add("추가불가");  // Runtime 에러발생
		// list2.remove(0); // 삭제불가, Runtime 에러발생
		for(String name:list2) {
			System.out.print(name + ", ");
		}
		System.out.println();
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		int sum = 0;
		for(Integer number:numbers) {
			sum += number;
		}
		System.out.println("1~10까지 합 = " + sum);
	}

}














