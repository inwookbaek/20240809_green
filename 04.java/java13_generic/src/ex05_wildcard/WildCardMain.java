package ex05_wildcard;

import java.util.Arrays;

/*
	와일드카드(<?>, <? extends 상위타입>, <? super 하위타입>)
	
	프로그램에서 ?을 일반적으로 와일드카드라고 한다. 제네릭타입을 매개값이나 리턴타입으로
	사용할 때 구체적인 타입대신에 와일드 카드를 사용할 수 있다. 와일드카드를 사용하는 형태는
	다음과 같이 3가지 형태로 사용할 수 있다.
	
	1. <?>                  : 타입의 제한이 없다.
	2. <? extends 상위타입> : 상위타입포함 하위타입만 가능하다. 즉 상위타입이상은 제한
	3. <? super 하위타입>   : 하위타입포함 상위타입만 가능하다. 즉 하위타입이하는 제한
*/
public class WildCardMain {

	public static void main(String[] args) {
		// 1. 일반인반 : 모든 사람이 수강등록이 가능한 코스
		Course<Person> 일반인과정 = new Course("일반인과정", 5);
		일반인과정.add(new Person("홍길동"));
		일반인과정.add(new Student("학생A"));
		일반인과정.add(new HighStudent("고등학생A"));
		일반인과정.add(new HighStudent("고등학생B"));
		일반인과정.add(new Worker("직장인A"));
		
		// 2. 학생반   : 학생만 수강등록이 가능한 코스
		Course<Student> 학생반 = new Course("학생반", 5);
		// 학생과정(new Person("홍길동"));
		// 학생과정(new Worker("손흥민"));
		학생반.add(new Student("대학생A"));
		학생반.add(new HighStudent("고등학생A"));
		System.out.println();
		
		// 3. 직장인반 : 직장인만 수강등록이 가능한 코스
		Course<Worker> 직장인반 = new Course("직장인반", 10);
		// 직장인반.add(new Person("일반인A"));
		// 직장인반.add(new Student("대학생A"));
		// 직장인반.add(new HighStudent("고등학생A"));
		직장인반.add(new Worker("근로자A"));
		직장인반.add(new Worker("홍길동"));
		직장인반.add(new Worker("손흥민"));
		직장인반.add(new Worker("소향"));
		
		
		// 메서드호출
		// A. 일반인
		System.out.println("--- 일반과정 ---");
		일반과정(일반인과정);
		System.out.println();
		
		// B.학생
		System.out.println("--- 학생과정 ---");
		학생과정(학생반);
		System.out.println();
		
		// C.직장인
		System.out.println("--- 직장인과정 ---");
		직장인과정(직장인반);
	}

	// 수강코스
	// 1. 일반인 (Person, Student, HighStudent, Worker)
	public static void 일반과정(Course<?> course) {
		System.out.println(course.getName() + ": 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	// 2. 학생 (Student and HighStudent)
	public static void 학생과정(Course<? extends Student> course) {
		System.out.println(course.getName() + ": 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	// 3. 직장인 (Worker)
	public static void 직장인과정(Course<? super Worker> course) {
		System.out.println(course.getName() + ": 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
}

class Course<T> {
	private String name;
	private T[] students;
	
	public Course(String name, int capacity) {
		this.name = name;
		this.students = (T[]) new Object[capacity];
	}
	
	public String getName() {
		return name;
	}
	
	public T[] getStudents() {
		return students;
	}
	
	public void add(T t) {
		for(int i=0;i<students.length;i++) {
			if(students[i] == null) {
				students[i] = t;
				break;
			}
		}
	}
}


class Person {
	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}

class Student extends Person {

	public Student(String name) {
		super(name);
	}
	
}

class HighStudent extends Student {

	public HighStudent(String name) {
		super(name);
	}
}

class Worker extends Person {

	public Worker(String name) {
		super(name);
	}
}
