package com.lec.ex02_set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class HashSetMain2 {

	public static void main(String[] args) {

		Set<Member> members = new HashSet<Member>();
		members.add(new Member(1, "홍길동"));
		members.add(new Member(2, "소향"));
		members.add(new Member(3, "홍길동"));  // 동명이인
		members.add(new Member(1, "홍길동"));  // 동일인
		System.out.println("회원수 = " + members.size());
		System.out.println();
		
		Iterator<Member> iterator = members.iterator();
		while(iterator.hasNext()) {
			Member member = iterator.next();
			System.out.println(member.toString());
		}
		 
	}

}

class Member {
	
	int mno;
	String name;
	
	public Member(int mno, String name) {
		this.mno = mno;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("회원번호 = %d, 회원이름 = %s, hashCode = %d"
				, this.mno, this.name, this.hashCode());
	}

	@Override
	public int hashCode() {
		return Objects.hash(mno, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return mno == other.mno && Objects.equals(name, other.name);
	}

}