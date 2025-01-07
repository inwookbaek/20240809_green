package com.lec.ex01_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListMain {

	public static void main(String[] args) {
		
		List<String> arrList = new ArrayList<String>();
		List<String> lnkList = new LinkedList<String>();
		
		// ArrayList vs LinkedList 비교
		// 1. add, remove일 경우
		long start, finish;
		
		// a. ArrayList
		start = System.nanoTime();
		
		for(int i=0;i<100000;i++) {
			arrList.add(0, String.valueOf(i));
		}
		
		finish = System.nanoTime();
		System.out.println("1. ArrayList 소요시간 = " + (finish - start) + "ns");
		
		// b. LinkedList
		start = System.nanoTime();
		
		for(int i=0;i<100000;i++) {
			lnkList.add(0, String.valueOf(i));
		}
			
		finish = System.nanoTime();
		System.out.println("2. LinkedList 소요시간 = " + (finish - start) + "ns");
	}

}














