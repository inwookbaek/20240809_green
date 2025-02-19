package com.lec.ex02_Objects;

import java.util.Comparator;
import java.util.Objects;

/*
	Objects클래스
	
	Object클래스와 유사한 이름을 가진 java.util.Objects클래스는 객체의 비교, 해시코드생성, 
	null여부, 객체문자열의 리넡등의 연산을 수행하는 static메서드로 구성된 Object의 유틸리
	티 클래스이다.
	
	1. 객체비교(compare(T a, T b, Comparator<T> c))
	
	   Objects.compare()메서드는 2 개의 객체를 비교해서 int값(-1, 0, 1)을 리턴한다. 
	   java.util.Compare<T>는 제네릭 인터페이스 타입으로 두 객체를 비교하는 compare(a, b)
	   메서드가 정의되어 있다.	
*/
public class CompareMain {

	public static void main(String[] args) {
		
		Student s1 = new Student(1);
		Student s2 = new Student(1);
		Student s3 = new Student(2);
		
		System.out.println("s1.hashCode = " + s1.hashCode());
		System.out.println("s2.hashCode = " + s2.hashCode());
		System.out.println("s3.hashCode = " + s3.hashCode());
		System.out.println();
		
		System.out.println("s1 == s2 : " + (s1 == s2));
		System.out.println("s1.equals(s2) : " + s1.equals(s2));
		System.out.println("s1.equals(s3) : " + s1.equals(s3));
		System.out.println();
		
		// hashCode와 equals를 재정의하지 않고 비교자를 별도로
		// 정의해서 객체를 비교하는 방법
		int result = Objects.compare(s1, s2, new StudentComparator());
		System.out.println("객체비교결과 = " + result);
		System.out.println();

		System.out.println("객체비교결과 = " + Objects.compare(s1, s3, new StudentComparator()));
		System.out.println("객체비교결과 = " + Objects.compare(s1, s2, new StudentComparator()));
		System.out.println("객체비교결과 = " + Objects.compare(s3, s1, new StudentComparator()));
		System.out.println();
		
		if(result == 0) {
			System.out.println("같은 객체입니다!!");
		} else {
			System.out.println("다른 객체입니다!!");
		}
	}

}

class Student {
	
	int sno;
	
	public Student(int sno) {
		this.sno = sno;
	}
	
}

// StudentComparator<Student>
class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		
		// 1. 조건문
		// if(s1.sno < s2.sno) return -1;
		// else if(s1.sno == s2.sno) return 0;
		// else return 1;
		
		// 2. 삼항연산
		// return (s1.sno < s2.sno ? -1 : (s1.sno == s2.sno ? 0 : 1));
		  
		// 3. Integer랩퍼클래스의 compare
		return Integer.compare(s1.sno, s2.sno);
	}
	
}
















