package com.lec.ex03_map.hashtable;

import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HashtableMain {

	public static void main(String[] args) {
		
		Map<String, String> map = new Hashtable<String, String>();
		
		// 로그인정보 : select id, pw from member;
		map.put("hong", "12345");
		map.put("sonny", "67890");
		map.put("sohyang", "abcde");
		map.put("kangin", "fghij");
		
		// containsKey() : 키존재여부 리턴
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("id와 pw를 입력하세요!!");
			System.out.println("id => ");
			String id = scanner.nextLine();
			System.out.println("pw => ");
			String pw = scanner.nextLine();
			
			if(map.containsKey(id)) {
				if(map.get(id).endsWith(pw)) {
					System.out.println("로그인 성공!!!");
				} else {
					System.out.println("로그인 실패!!!");
				}
			} else {
				System.out.println("id를 찾지 못했습니다! 다시 입력하세요!!");
				break;
			}
		}
		System.out.println("프로그램종료!!!");
	}

}
