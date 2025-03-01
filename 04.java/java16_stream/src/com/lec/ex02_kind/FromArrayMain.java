package com.lec.ex02_kind;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class FromArrayMain {

	public static void main(String[] args) {
		// 2. Arrays로 부터 Stream을 얻기
		String[] names = {"손흥민","이강인","소향","김민재","홍길동"};
		
		Stream<String> stream = Arrays.stream(names);
		stream.forEach(System.out :: println);
		System.out.println();
		
		int[] nums = {1,2,3,4,5,6,7,8,9,10};
		IntStream is = Arrays.stream(nums);
		is.forEach(n -> System.out.print(n + ", "));
		System.out.println();
		
		double[] d_nums = {1,2,3,4,5,6,7,8,9,10};
		DoubleStream ds = Arrays.stream(d_nums);
		ds.forEach(n -> System.out.print(n + ", "));
		System.out.println();
		
		long[] l_nums = {1,2,3,4,5,6,7,8,9,10};
		LongStream ls = Arrays.stream(l_nums);
		ls.forEach(n -> System.out.print(n + ", "));
		System.out.println();
		
	}

}
