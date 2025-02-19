package com.lec.ex04_while;

import java.io.IOException;
import java.util.Scanner;

public class DoWhileMain {

	public static void main(String[] args) throws IOException {
		// 키보드로부터 입력받은 방법
		// System.in.read()는 한개의 문자(키코드값(정수))만 읽기가 가능
		// 따라서 console에 입력된 문자열을 한번에 읽을 수가 업사.
		System.out.println("키보드에서 문자를 입력하세요 ==> ");
		System.out.println(System.in.read());
		
		// console에 입력된 문자열을 한번에 읽기 위해서는 Scanner객체를
		// 생성하고 nextLine()메서드를 호출해서 입력된 전체 문자열을 한
		// 번에 읽을 수가 있다.
		System.err.println("이름을 입력하세요!");
		System.out.println("프로그램을 종료하려면 q를 입력하세요!");
		
		String name = null;
		
		// 외부패키지의 라이브러리(클래스)를 import를 하려면
		// ctrl+space후 라이브러리선택 or
		// ctrl+shift+o하면 import하기 위한 전체 라이브러리가 import된다.
		Scanner scanner = new Scanner(System.in);
		
		do {
			System.out.println("이름을 입력하세요 => ");
			name = scanner.nextLine();
			System.out.println("입력된 이름 = " + name);
		} while(!name.equals("q"));
		
		System.out.println();
		System.out.println("프로그램이 종료되었습니다!!");		
	}
}