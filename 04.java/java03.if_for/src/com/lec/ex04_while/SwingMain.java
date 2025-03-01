package com.lec.ex04_while;

import javax.swing.JOptionPane;

public class SwingMain {

	public static void main(String[] args) {
		
		// Java에 swing은 GUI환경으로 인터페이스를 만들어 준다.
		String num = JOptionPane.showInputDialog("숫자를 입력하세요!!!");
		// System.out.println("입력된 값 = " + num);
		
		// 실습. 숫자맞추기게임
		int answer = 77;
		int inputNumber = Integer.parseInt(num);
		
		if(inputNumber == answer) {
			System.out.println("축하합니다! 정답입니다!");
		} else {
			System.out.println("정답이 아닙니다! 다음기회에 도전하세요!!");
		}
		
		// Yes=0, No=1, Cancle=2를 리턴
		System.out.println(JOptionPane.showConfirmDialog(null, num));

	}

}
