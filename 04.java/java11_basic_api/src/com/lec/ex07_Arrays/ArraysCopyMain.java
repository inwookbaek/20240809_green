package com.lec.ex07_Arrays;

import java.util.Arrays;

public class ArraysCopyMain {

	public static void main(String[] args) {
		
		char[] arr1 = {'J','A','V','A'};
		
		// 배열복사
		// 1. Arrays.copyOf();
		char[] chr1 = Arrays.copyOf(arr1, arr1.length);
		System.out.println(chr1);
		System.out.println(Arrays.toString(chr1));
		System.out.println();
		
		// 2. Arrays.copyOfRange(objcet, from, to);
		char[] chr2 = Arrays.copyOfRange(arr1, 1, 3);
		System.out.println(chr2);
		System.out.println(Arrays.toString(chr2));
		System.out.println();
				
		// 3. System.arraycopy();
		char[] chr3 = new char[arr1.length];
		System.arraycopy(chr1, 0, chr3, 0, arr1.length);
		System.out.println(chr3);
		System.out.println(Arrays.toString(chr3));
		System.out.println();
		

	}

}
