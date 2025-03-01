package com.lec.ex03_map.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class HashMapMain2 {

	public static void main(String[] args) {
		
		// key중복처리
		Map<Student, Integer> map1 = new HashMap<>();
		
		map1.put(new Student(1, "소향"), 90);
		map1.put(new Student(1, "소향"), 100);
		System.out.println("map1의 총 갯수 = " + map1.size());

		Set<Student> set = map1.keySet();
		Iterator<Student> students = set.iterator();
		while(students.hasNext()) {
			Student student = students.next();
			Integer score = map1.get(student);
			System.out.println(student + "의 점수 = " + score);
		}
	}

}

class Student {
	
	private int sno;
	private String name;
	
	public Student(int sno, String name) {
		super();
		this.sno = sno;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [sno=" + sno + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, sno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(name, other.name) && sno == other.sno;
	}	
}
