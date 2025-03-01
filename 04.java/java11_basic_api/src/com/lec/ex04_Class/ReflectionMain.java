package com.lec.ex04_Class;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionMain {

	public static void main(String[] args) throws Exception {

		Class _class = Class.forName("java.util.Calendar");
		// Class _class = Class.forName("com.lec.ex04_Class.Car");
		
		// 1. 클래스의 정보
		System.out.println(String.format("1. 클래스의 이름 = %s", _class.getName()));
		System.out.println();
		
		// 2. 생성자의 정보
		System.out.println("2. 생성자정보");
		Constructor[] constructors = _class.getDeclaredConstructors();
		System.out.println(String.format("... 생성자의 갯수 = %d", constructors.length));
		for(Constructor constructor:constructors) {
			System.out.println(
					String.format("... %s(%s)"
						, constructor.getName()
						, printParameters(constructor.getParameterTypes())));
		}
		System.out.println();
		
		// 3. 메서드의 정보
		System.out.println("3. 메서드정보");
		Method[] methods = _class.getDeclaredMethods();
		System.out.println(String.format("... 메서드의 갯수 = %d", methods.length));
		for(Method method:methods) {
			System.out.println(
					String.format("... %s(%s)"
						, method.getName()
						, printParameters(method.getParameterTypes())));
		}
		System.out.println();
		
		// 4. 필드의 정보
		System.out.println("4. 필드정보");
		Field[] fields = _class.getDeclaredFields();
		System.out.println(String.format("... 필드의 갯수 = %d", fields.length));
		for(Field field:fields) {
			System.out.println(
					String.format("... %s / %s"
							, field.getType().getSimpleName()
							, field.getName()));
		}
		
	}

	private static String printParameters(Class[] p) {
		String params = "";
		for(int i=0;i<p.length;i++) {
			params += p[i].getName() + (i<p.length-1 ? ", " : "");
		}
		return params;
	}

}
