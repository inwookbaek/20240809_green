package com.lec.ex02_supercall;

public class StudentMain {

	public static void main(String[] args) {
	
		Student student = new Student("소향", "991118-2234567", 1000);
		System.out.println(student);

		Student hong = new Student();
		hong.name = "홍길동";
		hong.ssn = "999999-1234567";
		hong.studentNo = 2000;
		System.out.println(hong);
		
		Student sonny = new Student(3000);
		sonny.name = "손흥민";
		sonny.ssn = "999998-1111111";
		System.out.println(sonny);
	}

}
