package com.lec.ex01_object.ex02_hashcode;

public class KeyMain {

	public static void main(String[] args) {
		
		Object obj1 = 0;
		Object obj2 = 0;
		Object obj3 = "0";
		Object obj4 = new String("0");
		
		System.out.println("obj1 = " + obj1.hashCode());
		System.out.println("obj2 = " + obj2.hashCode());
		System.out.println("obj3 = " + obj3.hashCode());
		System.out.println("obj4 = " + obj4.hashCode());
		System.out.println();
		
		System.out.println("obj1 == obj2 : " + (obj1 == obj2)); // true
		System.out.println("obj1 == obj3 : " + (obj1 == obj3)); // false
		System.out.println("obj1 == obj4 : " + (obj1 == obj4)); // false
		System.out.println();
		System.out.println("obj1.equals(obj2) : " + (obj1.equals(obj2))); // true
		System.out.println("obj1.equals(obj3) : " + (obj1.equals(obj3))); // false
		System.out.println("obj1.equals(obj4) : " + (obj1.equals(obj4))); // false
		System.out.println();
		
		System.out.println("obj1.toString() : " + obj1.toString());
		System.out.println("obj2.toString() : " + obj2.toString());
		System.out.println("obj3.toString() : " + obj3.toString());
		System.out.println("obj4.toString() : " + obj4.toString());
		System.out.println();
		
		Key key1 = new Key(0);
		Key key2 = new Key(0);
		System.out.println("key1 = " + key1.hashCode());
		System.out.println("key2 = " + key2.hashCode());
		System.out.println();
		
		System.out.println("key1 == key2 : " + (key1 == key2) ); // false
		System.out.println("key1.equals(key2) : " + (key1.equals(key2))); // false
		System.out.println(key1.hashCode() == key2.hashCode());
		
	}

}
