package com.lec.ex14_singleton;

public class Singleton {
	
	private Singleton() {}
	private static Singleton singleton = new Singleton();
	public static Singleton getSingleton() {
		return singleton;
	}
	
	public void setSingleton() {}
}
