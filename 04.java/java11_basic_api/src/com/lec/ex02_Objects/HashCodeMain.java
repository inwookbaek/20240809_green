package com.lec.ex02_Objects;

import java.util.Objects;

/*
	Objects클래스
	
	3. 해시코드(hash(), hashCode())
	
	   Objects.hash(Object...value)메서드는 주어진 값들을 이용해서 해시코드를 생성하는 
	   기능을 하는데 주어진 매개값들로 배열을 생서하고 Arrays.hashCode(Object[] a)를 호출
	   해서 해시코드를 얻고 이 값을 리턴한다.
	   
	   이 메서드는 클래스가 hashCode()를 재정의할 때 리턴값을 생성하기 위해 사용하면 좋다.
	   클래스가 여러가지 필드를 가지고 있을 때 이 필드들로 부터 해시코드를 생성하게 되면
	   동일한 필드값을 가지는 객체는 동일한 해시코드를 얻을 수가 있다.
*/
public class HashCodeMain {

	public static void main(String[] args) {
		
		Student1 s1 = new Student1(1, "홍길동");
		Student1 s2 = new Student1(1, "홍길동");
		
		System.out.println("s1.hashCode() = " + s1.hashCode());
		System.out.println("s2.hashCode() = " + s2.hashCode());

	}

}

class Student1 {
	int sno;
	String name;
	String ssn = "9911181234567";
	
	public Student1(int sno, String name) {
		super();
		this.sno = sno;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, sno, ssn);
	}
	
}




















