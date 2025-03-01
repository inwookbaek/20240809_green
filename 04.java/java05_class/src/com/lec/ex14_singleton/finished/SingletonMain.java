package com.lec.ex14_singleton.finished;

import java.util.Calendar;

public class SingletonMain {

	public static void main(String[] args) {
		
		Singleton sing1 = Singleton.getInstance();
		Singleton sing2 = Singleton.getInstance();
		Singleton sing3 = Singleton.getInstance();
		System.out.println("sing1 = " + sing1.hashCode());
		System.out.println("sing2 = " + sing2.hashCode());
		System.out.println("sing3 = " + sing3.hashCode());		
		System.out.println();
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		System.out.println("cal1 = " + cal1.hashCode());
		System.out.println("cal2 = " + cal2.hashCode());
		

	}

}
