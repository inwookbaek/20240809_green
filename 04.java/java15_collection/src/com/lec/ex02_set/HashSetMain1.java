package com.lec.ex02_set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
	Set 컬렉션
	
	List컬렉션은 저장순서를 유지하지만 Set컬렉션은 저장순서를 유지하지 않으며 또한 객체를
	중복저장할 수 없다. (null은 저장가능하지만 단 한개의 null값만 저장할 수 있다.)
	
	set컬렉션은 HashSet, TreeSet, LinkedHashSet등이 있다. set컬렉션은 순서가 없어서 인덱스
	로 객체를 검색할 수 없다. 대신에 전체 객체를 한번씩 반복해서 읽어 오는 반복자(Iterator)
	를 제공한다. 반복자는 Iterator인터페이스를 구현한 객체를 말하는데 iterator()메서드를 호
	출해서 자료를 읽을 수가 있다.
	
	Iterator에는 아래와 같은 메서드를 제공한다.
	
	1. hasNext() : 읽어올 객체가 있을 경우에는 true, 없을 경우에는 false를 리턴
	2. next()    : set컬렉션에서 다음 객체를 읽어오는 메서드, 더 이상 읽을 객체가 없다면 예외발생
	3. remove()  : set컬렉션에서 객체를 제거
	
	HashSet은 객체를 순서없이 저장하고 동일한 객체는 중복저장하지 않는다. 하지만 HashSet이 판단하
	는 동일 객체란 꼭 같은 인스턴스를 뜻하지는 않는다. HashSet객체를 저장하기 전에 먼저 객체의 
	hashCode()메서드를 호출해서 해시코드를 가져오로 이미 저장되어 있는 객체들의 hashCode와 비교
	한다. 만약, 동일한 hashCode가 있다면 다시 equals()메서드를 호출해서 두 객체를 비교해서 같으면
	true를 리턴하고 true가 리턴되면 동일객체로 판단하고 더이상 저장하지 않는다.
*/
public class HashSetMain1 {

	public static void main(String[] args) {
		
		Set<String> set = new HashSet<String>();
		
		// 1. 추가
		set.add("Java");
		set.add("DataBase");
		set.add("Python");
		set.add("JDBC");
		set.add("R");
		set.add("Java");   // 에러발생하지 않지만 중복저장이 되지 않는다.
		set.add("SQL");
		System.out.println("set의 총 갯수 = " + set.size());
		System.out.println();
		
		// 2. Set객체 읽기 - Iterator객체
		Iterator<String> iterator = set.iterator();
		System.out.println("iterator에 요소가 있는지? = " + iterator.hasNext());
		while(iterator.hasNext()) {
			String element = iterator.next(); // element를 무작위로 꺼내오고 해당 element 삭제
			System.out.println(element);
		}
		System.out.println("set의 총 갯수 = " + set.size());
		System.out.println("iterator에 요소가 있는지? = " + iterator.hasNext());
		
		// iterator.next(); // 더 이상읽을 요소가 없기 때문에 Runtime(NoSuchElementException) 에러발생
		
		iterator = set.iterator();
		System.out.println("iterator에 요소가 있는지? = " + iterator.hasNext());
		System.out.println();
		
		String data = iterator.next();
		System.out.println("무작위로 꺼내온 요소 = " + data);
		System.out.println();
		
		// 3. Set 요소삭제
		set.remove("Java");
		System.out.println("set의 총 갯수 = " + set.size());
		iterator = set.iterator();
		while(iterator.hasNext()) {
			String element = iterator.next(); 
			System.out.println(element);
		}	
		System.out.println();
		
		// 4. 전체삭제
		set.removeAll(set); // or set.clear();
		System.out.println("set의 총 갯수 = " + set.size());
		
	}

}









