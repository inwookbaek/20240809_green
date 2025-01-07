package com.lec.ex01_intro;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelMain {

	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("손흥민","이강인", "소향", "홍길동", "홍길순");
		Stream<String> stream = null;  // 반복자이기 때문에 다 읽은 후에 다시 읽을 경우 재로딩
		
		// 1. 직렬(순차)처리 : stream()	
		stream = list.stream();
		stream.forEach(n -> System.out.print(n + ", "));
		System.out.println("\n");
		
		stream = list.stream();
		stream.forEach(n -> ParallelMain.print(n));
		System.out.println();
		
		stream = list.stream();
		stream.forEach(ParallelMain :: print);		
		System.out.println();
			
		// 2. 병렬처리 : parallelStream()
		stream = list.parallelStream();
		stream.forEach(ParallelMain::print);
	}

	
	public static void print(String name) {
		System.out.println(name + " : " + Thread.currentThread().getName());
	}
}















