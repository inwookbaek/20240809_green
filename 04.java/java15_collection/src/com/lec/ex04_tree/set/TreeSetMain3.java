package com.lec.ex04_tree.set;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetMain3 {

	public static void main(String[] args) {
		// 3. 검색하기
		TreeSet<String> words = new TreeSet<String>();
		words.add("apple");
		words.add("c");
		words.add("forever");
		words.add("description");
		words.add("love");
		words.add("zoo");
		words.add("ever");
		words.add("base");
		words.add("guess");
		words.add("cherry");
		words.add("f");
		for(String word:words) {
			System.out.print(word + ", ");
		}
		System.out.println();
		
		// 범위검색
		System.out.println("\n[c~f]사이의 단어 검색하기(c,f포함)");
		NavigableSet<String> rangeWords =  words.subSet("c", true, "f", true);
		for(String word:rangeWords) {
			System.out.print(word + ", ");
		}
		System.out.println();
		
		System.out.println("\n[c~f]사이의 단어 검색하기(c, f불포함)");
		rangeWords =  words.subSet("c", false, "f", false);
		for(String word:rangeWords) {
			System.out.print(word + ", ");
		}
		System.out.println();
		
	}

}













