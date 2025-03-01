package com.lec.ex07_summary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

public class ListMain {

	public static void main(String[] args) throws Exception {
		
		// 1. Collection : 순서, 중복
		// 1) List(ArrayList, Vector, LinkedList)
		//    ... ArrayList vs Vector 차이점 : Vector는 스레드환경에 안전, ArrayList 안전(x)
		//    ... ArrayList vs LinkedList차이점
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new Vector<>();
		List<String> list3 = new LinkedList<>();
		
		// method : add(idx), get(idx), remove(idx), clear();
		list1.add("손흥민");
		list1.add("이강인");
		list1.add("손흥민");
		System.out.println("총요소갯수 = " + list1.size());
		System.out.println("1번째요소 = " + list1.get(0));
		System.out.println("2번째요소 = " + list1.get(1));
		System.out.println("3번째요소 = " + list1.get(2));
		System.out.println();
		
		list1.remove(1);
		System.out.println("총요소갯수 = " + list1.size());
		System.out.println("1번째요소 = " + list1.get(0));
		System.out.println("2번째요소 = " + list1.get(1));
		// System.out.println("3번째요소 = " + list1.get(2)); index out of bounded exception
		
		list1.add(1, "소향");
		for(String list:list1) {
			System.out.print(list + ", ");
		}
		System.out.println();
		
		for(int i=0;i<list1.size();i++) {
			System.out.print(list1.get(i) + ", ");
		}
		System.out.println();
		
		list1.clear();
		System.out.println("총요소갯수 = " + list1.size());
		System.out.println("-".repeat(60));
		
		// 2) Set(HashSet, TreeSet) - 순서(x), 중복(x)
		// Iterator반복자객체생성 후 while문으로 데이터를 읽기(무작위)
		Set<String> set1 = new HashSet<>();
		
		// add, iterator, remove, clear
		set1.add("손흥민");
		set1.add("이강인");
		set1.add("손흥민");  // 중복불가

		System.out.println("총요소갯수 = " + set1.size());
		// System.out.println("1번째요소 = " + set1.get(0)); // set은 순서가 없다.
		
		// Iterator : hasNext(), next(), isEmpty...
		// next메서드는 데이터를 1건 꺼내온 후 삭제
		// 더이상 읽을 자료가 없을 때 next 즉, 데이터를 읽으려면 에러 발생
		Iterator<String> data = set1.iterator();
		while(data.hasNext()) {
			System.out.print(data.next() + ", ");
		}
		// data.next(); // NoSuchElementException
		System.out.println();

		set1.add("소향");
		data = set1.iterator();
		while(data.hasNext()) {
			System.out.print(data.next() + ", ");
		}
		System.out.println();
		
		set1.remove("손흥민");
		System.out.println("총요소갯수 = " + set1.size());
		
		set1.clear();
		System.out.println("총요소갯수 = " + set1.size());
		System.out.println();
		
		Set<Student> students = new HashSet<>();
		// students.add("문자열");
		students.add(new Student(1, "손흥민"));		
		students.add(new Student(2, "이강인"));		
		students.add(new Student(1, "손흥민"));		
		students.add(new Student(3, "소향"));		
		System.out.println("총요소갯수 = " + students.size());
		
		for(Student student:students) {
			System.out.print(student.toString() + ", ");
		}
		System.out.println();	
		System.out.println("-".repeat(60));
		
		// 2. Map : key와 value를 한쌍으로 갖는 컬렉션
		// HashMap, Hashtable, Properties
		// 1) HashMap vs Hashtable : HashMap은 스레드환경 안전(x) Hashtable은 스레드환경 안전(o)
		Map<String, Integer> map1 = new HashMap<>();
		
		// put, entrySet, keySet, values, get(key)
		map1.put("손흥민", 90);
		map1.put("이강인", 95);
		map1.put("손흥민", 100);
		System.out.println("총요소갯수 = " + map1.size());
		System.out.println("손흥민의 점수 = " + map1.get("손흥민"));
		System.out.println();
		
		Set<String> names = map1.keySet();
		Iterator<String> data1 = names.iterator();
		while(data1.hasNext()) {
			String name = data1.next();
			System.out.println(name + "의 점수 = " + map1.get(name));
		}
		System.out.println();
		
		Set<Map.Entry<String, Integer>> entrySet= map1.entrySet();
		Iterator<Map.Entry<String, Integer>> data2 = entrySet.iterator();
		while(data2.hasNext()) {
			Map.Entry<String, Integer> entry = data2.next();
			String name = entry.getKey();
			Integer score = entry.getValue();
			System.out.println(name + "의 점수 = " + score);			
		}
		System.out.println();
		
		// 2) Properties는 키와 값이 Only String만 갖는 map계열
		Properties prop = new Properties(); // Hashtable의 하위객체
		String path = ListMain.class.getResource("./data.properties").getPath();
		path = URLDecoder.decode(path, "utf-8"); // 패스에 한글이 있다면 utf-8 디코딩
		prop.load(new FileReader(path));
		
		String name = prop.getProperty("name");
		String score = prop.getProperty("score");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		
		System.out.println(name + "의 점수 = " + score);
		System.out.println(user + "의 비밀번호 = " + password);
		
	}

}

class Student {
	private int sno;
	private String name;
	
	public Student(int sno, String name) {
		this.sno = sno;
		this.name = name;
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
   
	@Override
	public String toString() {
		return this.name;
	}
}

class Member {
	private String id;
	private String name;
}
