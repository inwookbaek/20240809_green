package com.lec.ex04_tree.map;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeMapMain2 {

	public static void main(String[] args) {
		// 2. 정렬하기
		TreeSet<Integer> scores = new TreeSet<>();
		scores.add(87);
		scores.add(98);
		scores.add(75);
		scores.add(95);
		scores.add(80);			

		// 1) 자동정렬하기
		System.err.print("[오름차순] ");
		for(Integer score:scores) {
			System.out.print(score + ", ");
		}
		System.out.println();
		
		// 2) 내림차순, 오름차순으로 정렬
		// descendingSet()      : 내림차순, NavigableSet객체로 리턴
		// descendingIterator() : 내림차순, Iterator객체로 리턴
		NavigableSet<Integer> descSet = scores.descendingSet();
		System.err.print("[내림차순] ");
		for(Integer score:descSet) {
			System.out.print(score + ", ");
		}
		System.out.println();
		
		// 3) 내림차순 -> 오름차순
		NavigableSet<Integer> ascSet = descSet.descendingSet();
		System.err.print("[내림->오름] ");
		for(Integer score:ascSet) {
			System.out.print(score + ", ");
		}
		System.out.println();
	}

}















