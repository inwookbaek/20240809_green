package com.lec.ex14_singleton;

public class SingletonMain {

	public static void main(String[] args) {
		// 1. 생성자가 public접근제한자 일 경우
//		Singleton sing1 = new Singleton();
//		Singleton sing2 = new Singleton();
//		Singleton sing3 = new Singleton();
//		
//		System.out.println("sing1 = " + sing1.hashCode());
//		System.out.println("sing2 = " + sing2.hashCode());
//		System.out.println("sing3 = " + sing3.hashCode());
		
		// 2. 필드(singleton)가 private접근제한자일 경우
//		Singleton sing1 = Singleton.singleton;
//		Singleton sing2 = Singleton.singleton;
//		Singleton sing3 = Singleton.singleton;
//		System.out.println("sing1 = " + sing1.hashCode());
//		System.out.println("sing2 = " + sing2.hashCode());
//		System.out.println("sing3 = " + sing3.hashCode());
		
		// 3. 필드(singleton)가 private접근제한자 & getter
		Singleton sing1 = Singleton.getSingleton();
		Singleton sing2 = Singleton.getSingleton();
		Singleton sing3 = Singleton.getSingleton();
		System.out.println("sing1 = " + sing1.hashCode());
		System.out.println("sing2 = " + sing2.hashCode());
		System.out.println("sing3 = " + sing3.hashCode());			
		
	}

}
