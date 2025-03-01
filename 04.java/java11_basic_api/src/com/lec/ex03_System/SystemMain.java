package com.lec.ex03_System;

import java.util.Map;

/*
	System 클래스
	
	자바프로그램은 운영체제에서 실행되는 것이 아니라 JVM위에서 실행되기 때문에 운영체제의 
	모든 기능을 자바코드가 직접 수행(개발)하기란 매우 어려운 작업이다.
	하지만, java.lang.System클래스를 이용하면 운영체제의 일부기능들을 이용할 수가 있다.
	
	프로세스의 종료(exit), 키보드 입력(scanner()), 콘솔입출력(in(), out()), 메모리정리(gc())
	현재시간(nanoTime()...)등의 정보를 얻을 수가 있다.
	
	1. 프로그램종료(exit())
	
	   경우에 따라서 프로세스(JVM)을 강제로 종료시킬 필요가 있는 경우도 있다. 이때 System.exit(int)
	   메서드를 호출하면 현재 실행하고 있는 프로세스를 강제로 종료시킬 수가 있다.
	   
	   exit()메서드는 int매개값을 지정하도록 되어 있는데 이 값을 종료상태값이라고 한다. 정상종료일
	   경우는 0, 비정상종료일 경우는 "0이외의 값"을 지정한다.
	
	2. 쓰레기수집(gc())
	
	   쓰레기 수집기는 개발자가 직접 코드를 실행시킬 수 없고 JVM에게 가능한 빠른 시간내에 실행하도록
	   요청할 수 있다. 이 기능을 하는 것이 System.gc()메서드이다. gc메서드는 호출하면 곧나로 실행되
	   는 것이 아니라 JVM이 빠른 시간애에 실행시키기 위해 노력한다.
	
	3. OS환경정보(getenv())
	
	   getenv()메서드는 시스템의 운영체제의 환경정보를 읽을 수가 있다.

*/
public class SystemMain {

	public static void main(String[] args) {
		
		// 1. exit()
		if(args.length > 4) {
			System.out.println("프로그램종료!!");
			System.exit(0);  // 관례적으로 0은 정상종료, 이외는 비정상종료
		}
		
		// 2. gc()
		Employee emp = null;
		
		for(int i=0;i<3;i++) {
			emp = new Employee(i);
			if(i==1) emp = null;
		}
		System.gc();
		System.out.println();
		
		// 3. getenv() 
		String java_home = System.getenv("JAVA_HOME");
		System.out.println("JAVA_HOME = " + java_home);
		
		String path = System.getenv("Path");
		System.out.println("path = " + path);
		System.out.println();
		
		// 4. 전체환경정보
		// Map자료구조(Collection)는 key와 value을 한쌍으로 갖는 데이터구조
		for(Map.Entry entry: System.getenv().entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
		System.out.println();
		
		System.out.println("프로그램이 정상적으로 종료되었습니다!!");
	}

}

class Employee {
	int empno;
	
	public Employee(int empno) {
		System.out.println(String.format("Employee(%d)가 메모리에 생성되었습니다!", this.empno ));
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println(String.format("Employee(%d)가 메모리에서 제거되었습니다!", this.empno ));
	}
}













