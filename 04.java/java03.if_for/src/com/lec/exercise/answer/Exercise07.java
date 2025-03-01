package com.lec.exercise.answer;

import javax.swing.JOptionPane;

public class Exercise07 {

	public static void main(String[] args) {
		
		int menuNo = 0;
		int money = 0;
		int balance = 0;
		
		do {
			try {
				menuNo = Integer.parseInt(JOptionPane.showInputDialog("1. 예금\n2. 출금\n3. 조회\n4. 종료\n"));
				
				if(menuNo==1) {
					money = Integer.parseInt(JOptionPane.showInputDialog("입금할 금액을 입력하세요!!"));
					balance += money;
				} else if(menuNo==2) {
					money = Integer.parseInt(JOptionPane.showInputDialog("출금할 금액을 입력하세요!!"));
					balance -= money;
				} else if(menuNo==3) {
					JOptionPane.showConfirmDialog(null,
				             String.format("현재잔액은 %,15d 원 입니다!", balance)
							, "예금잔액", JOptionPane.OK_OPTION);
				} 
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null
						, "숫자가 아닙니다! 다시 입력하세요!"
						, "에러발생!!"
						, JOptionPane.ERROR_MESSAGE);
//				JOptionPane.showConfirmDialog(null
//						,"숫자가 아닙니다! 다시 입력하세요!"
//						, "에러발생!!", JOptionPane.OK_OPTION);
				menuNo = 0;
			}	
		} while(menuNo != 4);

	}

}
