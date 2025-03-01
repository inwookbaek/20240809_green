package com.lec.ex08_object;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableMain {

	public static void main(String[] args) throws Exception {
		
		FileOutputStream fos = new FileOutputStream("src/com/lec/ex08_object/class_a.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		// 1. 객체의 직렬화
		ClassA class_a = new ClassA();
		class_a.field1 = 1000;
		class_a.field2.field1 = 2000;
		class_a.field3 = 3000;
		class_a.field4 = 4000;
		
		oos.writeObject(class_a);
		
		oos.flush();
		oos.close();
		fos.close();
	
		// 2. 객체의 역직렬화
		FileInputStream fis = new FileInputStream("src/com/lec/ex08_object/class_a.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ClassA obj = (ClassA) ois.readObject();
		System.out.println("ClassA.field1 = " + obj.field1);
		System.out.println("ClassA.field2 = " + obj.field2);
		System.out.println("ClassA.field2 = " + obj.field2.field1);
		System.out.println("ClassA.field3 = " + obj.field3); // 역직화(x) 즉, 공통영역의 값
		System.out.println("ClassA.field4 = " + obj.field4); // 역직렬화(x)
		
		ois.close();
		fis.close();

	}

}

class ClassA implements Serializable {
	// 1. 직렬화에 포함
	int field1;
	ClassB field2 = new ClassB();
	
	// 2. 직렬화에 불포함
	static int field3;
	transient int field4;
	public ClassA() {}
	public void method() {}
}

class ClassB implements Serializable {
	int field1;
}