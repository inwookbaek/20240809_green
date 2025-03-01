package com.lec.ex05_user;

public class AccountMain {

	public static void main(String[] args) throws 잔액부족 {
		
		Account account = new Account();
		account.예금(100);
		System.out.println("현재잔액 = " + account.getBalance() + "만원");
		account.출금(50);
		System.out.println("현재잔액 = " + account.getBalance() + "만원");
		account.출금(60);
	}

}
