package com.lec.ex01_object.ex01_equals;

public class MemberMain {

	public static void main(String[] args) {
		
		Member member1 = new Member("hong");
		Member member2 = new Member("hong");
		Member member3 = new Member("kim");
		
		System.out.println("member1 = " + member1.hashCode() + ", toString() = " + member1.toString());
		System.out.println("member2 = " + member2.hashCode() + ", toString() = " + member2.toString());
		System.out.println("member3 = " + member3.hashCode() + ", toString() = " + member3.toString());
		System.out.println();
		
		// 1. id가 동일하다면 동일객체라고 간주
		System.out.println("member1 == member2 : " + (member1 == member2)); // true를 기대하지만 false
		System.out.println("member1 == member3 : " + (member1 == member3)); // true를 기대하지만 false
		
		// 2. 객체의 비교(equals())
		System.out.println("member1.equals(member2) : " + (member1.equals(member2))); // true를 기대하지만 false
		System.out.println("member1.equals(member3) : " + (member1.equals(member3))); // true를 기대하지만 false
		System.out.println();
		
		if(member1.equals(member3)) {
			System.out.println("동일객체입니다!!");
		} else {
			System.out.println("다른객체입니다!!");			
		}
	}

}
