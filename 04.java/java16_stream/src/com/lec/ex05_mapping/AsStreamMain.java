package com.lec.ex05_mapping;

import java.util.Arrays;
import java.util.stream.IntStream;

public class AsStreamMain {

	public static void main(String[] args) {
		
		int[] int_arr = {1,2,3,4,5};
		
		// 1. asDolubleStream() : int타입을 double로 변환
		IntStream int_stream =  Arrays.stream(int_arr);
		int_stream.asDoubleStream().forEach(System.out::println);
		System.out.println();
		
		// 2. boxed() : int, long, double -> Integer, Long, Double로 boxing
		int_stream =  Arrays.stream(int_arr);
		int_stream.boxed().forEach(기본타입 -> {
			System.out.println(기본타입.getClass() + " : " + 기본타입.intValue());
		});

	}

}
