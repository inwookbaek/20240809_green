package com.lec.ex04_tree.map;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapMain3 {

	public static void main(String[] args) {
		// 3. 범위검색하기
		TreeMap<String, Integer> words = new TreeMap<String, Integer>();
		words.put("apple", 10);
		words.put("orange", 15);
		words.put("banana", 10);
		words.put("cherry", 10);
		words.put("mango", 10);
		words.put("pea", 10);
		words.put("for", 10);
		words.put("c", 10);
		words.put("f", 10);
		
		// c~f사이의 단어검색하기
		System.err.println("\n[c~f]사이의 단어 검색하기(c,f포함)");
		NavigableMap<String, Integer> rangeWords = words.subMap("c", true, "f", true);
		for(Map.Entry<String, Integer> word:rangeWords.entrySet()) {
			System.out.println(word.getKey() + " = " + word.getValue());
		}
		System.out.println();
		
		System.err.println("\n[c~f]사이의 단어 검색하기(c 불포함)");
		rangeWords = words.subMap("c", false, "f", false);
		for(Map.Entry<String, Integer> word:rangeWords.entrySet()) {
			System.out.println(word.getKey() + " = " + word.getValue());
		}

	}

}
