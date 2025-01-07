package com.lec.ex11_collect;

import java.util.Arrays;
import java.util.List;
import java.util.Map;import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectionMain3 {

	public static void main(String[] args) {
		List<Student> list = Arrays.asList(
				new Student("홍길동", 90, Student.Gender.MALE, Student.City.SEOUL),
				new Student("홍길순", 88, Student.Gender.FEMALE, Student.City.PUSAN),
				new Student("홍길녀", 89, Student.Gender.FEMALE, Student.City.SEOUL),
				new Student("홍길상", 91, Student.Gender.MALE, Student.City.PUSAN)
			);	
		
		// 1. 성별로 그룹핑
		// MALE:{홍길동, 홍길상}, FEMAL:{홍길순, 홍길녀}
		Map<Student.Gender, List<Student>>
			by_gender = list.stream().collect(Collectors.groupingBy(Student::getGender));
		System.out.println(by_gender.getClass());
		System.out.println(by_gender.get(Student.Gender.MALE).getClass());
		
		System.out.print ("[남학생] ");
		by_gender.get(Student.Gender.MALE).stream().forEach(s -> System.out.print(s.getName() + ", "));
		System.out.println();
		
		System.out.print("[여학생] ");
		by_gender.get(Student.Gender.FEMALE).stream().forEach(s -> System.out.print(s.getName() + ", "));
		System.out.println("\n");
		
		
		// 2. 실습. 도시별로 그룹핑
		Map<Student.City, List<Student>>
			by_city = list.stream().collect(Collectors.groupingBy(Student::getCity));
		
		System.out.print ("[서울] ");
		by_city.get(Student.City.SEOUL).stream().forEach(s -> System.out.print(s.getName() + ", "));
		System.out.println();
		
		System.out.print ("[부산] ");
		by_city.get(Student.City.PUSAN).stream().forEach(s -> System.out.print(s.getName() + ", "));
		System.out.println("\n");
		
		// 3. Collectors.groupingBy(그룹핑조건, 매핑(집계))
		// Collectors.groupingBy() 메서드는 그룹핑후에 매핑이나 집계(평균,합계...)를 할 수 있도록
		// 2번째 매개변수로 Collectors객체를 정의할 수 있다.
		Map<Student.City, List<String>> 
			by_city1 = list.stream()
						   .collect(Collectors.groupingBy(Student::getCity
								   , Collectors.mapping(Student::getName, Collectors.toList())));
		System.out.print ("[서울] ");
		by_city1.get(Student.City.SEOUL).stream().forEach(name -> System.out.print(name + ", "));
		System.out.println();
		
		System.out.print ("[부산] ");
		by_city1.get(Student.City.PUSAN).stream().forEach(name -> System.out.print(name + ", "));
		System.out.println("\n");		
		
	}

}
















