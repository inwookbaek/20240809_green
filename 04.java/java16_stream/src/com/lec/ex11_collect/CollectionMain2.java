package com.lec.ex11_collect;

import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

public class CollectionMain2 {

	public static void main(String[] args) {
		
		List<Student> list = Arrays.asList(
				new Student("홍길동", 90, Student.Gender.MALE),
				new Student("홍길순", 88, Student.Gender.FEMALE),
				new Student("홍길녀", 89, Student.Gender.FEMALE),
				new Student("홍길상", 91, Student.Gender.MALE)
			);
		
		MaleStudent 남학생 = list.stream()
				.filter(s -> s.getGender() == Student.Gender.MALE)
				//  collect(Collector<? super T, A, R> collector);
				// .collect(() -> new MaleStudent(), (m, s) -> m.accumulate(s), (m1, m2) -> m1.combine(m2))
				.collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine);
		남학생.getList().stream().forEach(s -> System.out.print(s.getName() + ", "));
		
		// 남학생을 사용자컨테이너(MaleStudent)에 저장(수집)하는 과정
		// 1) Stream<Student> tot_stream = list.stream()
		// 2) Stream<Student> male_stream = tot_stream.filter(s -> s.getGender() == Student.Gender.MALE)
		// 3) Supplier<MaleStudent> supplier = () -> new MaleStudent()
		// 4) BiConsumer<MaleStudent, Student> accumulator = (m, s) -> m.accumulate(s)
		// 5) BiConsumer<MaleStudent, MaleStudent> combine = (m1, m2) -> m1.combine(m2)
	}
}

class MaleStudent {
	// 남학생들만 저장할 Collection
	private List<Student> list;

	public MaleStudent() {
		this.list = new ArrayList<Student>();
		System.out.println("[" + Thread.currentThread().getName() +"] 생성자를 호출한 쓰레드");
	}
		
	// 요소들을 저장할 메서드
	public void accumulate(Student student) {
		this.list.add(student);
		System.out.println("[" + Thread.currentThread().getName() +"] accumulate()를 호출한 쓰레드");
		
	}
	
	// 두개의 MaleStudent를 결합하는 메서드(병렬처리에서만 처리)
	public void combine(MaleStudent student) {
		this.list.addAll(student.getList());
		System.out.println("[" + Thread.currentThread().getName() +"] combine()를 호출한 쓰레드");
	}

	public Collection<Student> getList() {
		return this.list;
	}
	
}