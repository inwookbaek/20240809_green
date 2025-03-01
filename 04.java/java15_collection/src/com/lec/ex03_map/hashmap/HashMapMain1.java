package com.lec.ex03_map.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
	Map Collection
	
	Map컬렉션은 키(Key)와 값(Value)으로 구성된 Map.Entry객체를 저장하는 구조를 가지고 있다.
	키와 값은 모두 참조타입(객체)이다. 키는 중복이 불가하고 값은 중복저장이 가능하다. 
	만약, 기존에 저장된 키와 동일한 키가 있다면 기존값은 없어지고 새로운 값을 대체된다.
	
	Map컬렉션은 HashMap, HashTable, Properties, TreeMap등이 있다. Map에 대한 값을 얻고자할 
	경우에는 2가지 방법이 있다.
	
	키값을 알고 있다면 get()메서드로 간단히 객체를 검색할 수 있지만 저장된 전체 객체를 대상
	으로 값을 하나씩 일고자 할 경우에는
	
	1. keySet()  : 이 메서드로 모든 키를 set컬렉션으로 저장한 후에 반복자(Iterator)를 통해서
	               키를 하나씩 읽은 후에 get()메서드를 이용해서 값을 읽는 방법 
	2. entrySet(): 이 메서드로 모든 Map.Entry객체를 set컬렉션으로 읽은 후 반복자를 통해 
	               Map.Entry객체를 하나씩 읽어서 getKey(), getValue()메서드를 이용해서 읽는
	               방법이 있다.
*/
public class HashMapMain1 {

	public static void main(String[] args) {
		
		// 1. Map객체생성
		Map<String, Integer> map1 = new HashMap<>();
		
		// 2. 객체에 요소를 추가
		map1.put("홍길동", 85);   // 85 -> Integer.valueOf(85);
		map1.put("손흥민", Integer.valueOf(80));
		map1.put("홍길자", 95);
		map1.put("홍길녀", 75);
		map1.put("홍길순", 88);
		map1.put("손흥민", 99);  // 손흥미의 key가 중복, 에러는 발생하지 않고 80 -> 99로 대체
		System.out.println("map1의 총 갯수 = " + map1.size());

		Map<Integer, String> map2 = new HashMap<Integer, String>();
		map2.put(1, "소향");
		map2.put(1, "소향");
		map2.put(1, "나얼");
		System.out.println("map2의 총 갯수 = " + map2.size());
		System.out.println();
		
		// 3. 객체검색(1) - get()
		System.out.println("map1 : 손흥민의 점수 = " + map1.get("손흥민"));
		System.out.println("map2 : 1 번의 이름   = " + map2.get(1));
		System.out.println();
		
		// 4. 객체검색(2) - keySet()
		Set<String> set1 = map1.keySet();
		Iterator<String> names = set1.iterator();
		while(names.hasNext()) {
			String name = names.next();
			Integer score = map1.get(name);
			System.out.println(name + "의 점수 = " + score);
		}
		System.out.println();
		
		// 5. 객체검색(3) - entrySet()
		Set<Map.Entry<String, Integer>> set2 = map1.entrySet();
		Iterator<Map.Entry<String, Integer>> students = set2.iterator();
		while(students.hasNext()) {
			Map.Entry<String, Integer> student = students.next();
			String name = student.getKey();
			Integer score = student.getValue();
			System.out.println(name + "의 점수 = " + score);
		}
		System.out.println();
		
		// 6. 객체삭제
		map1.remove("홍길동");
		set1 = map1.keySet();
		names = set1.iterator();
		while(names.hasNext()) {
			String name = names.next();
			Integer score = map1.get(name);
			System.out.println(name + "의 점수 = " + score);
		}
		System.out.println("map1의 총 갯수 = " + map1.size());
		System.out.println();		
		
		// 7. 전체삭제
		map1.clear();
		System.out.println("map1의 총 갯수 = " + map1.size());		
	}

}