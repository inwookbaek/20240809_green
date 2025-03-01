package com.lec.ex05_compare;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class ComparatorMain {

	public static void main(String[] args) {
		
		TreeSet<Member> members = new TreeSet<>(new MemberCompartor());
		TreeSet<Member> ts = new TreeSet<>(new Comparator<Member>() {
			@Override
			public int compare(Member m1, Member m2) {
				int x = m1.name.hashCode() + m1.mno;
				int y = m2.name.hashCode() + m2.mno;
				return Integer.compare(x, y);
			}
		});
		
		members.add(new Member(10, "홍길동"));
		members.add(new Member(2, "홍길순"));
		members.add(new Member(3, "홍길자"));
		members.add(new Member(4, "홍길동"));
		members.add(new Member(5, "손흥민"));
		members.add(new Member(6, "이강인"));
		members.add(new Member(7, "김민재"));
		members.add(new Member(8, "나얼"));
		members.add(new Member(9, "소향"));
		System.out.println("students의 총 갯수 = " + members.size());
		System.out.println();		
		
		Iterator<Member> member = members.iterator();
		while(member.hasNext()) {
			Member mbr = member.next();
			System.out.println(mbr.name + ", " + mbr.mno);
		}
	}
}

class Member {
	
	int mno;
	String name;
	
	public Member(int mno, String name) {
		super();
		this.mno = mno;
		this.name = name;
	}	
}

class MemberCompartor implements Comparator<Member> {

	@Override
	public int compare(Member m1, Member m2) {
		int x = m1.name.hashCode() + m1.mno;
		int y = m2.name.hashCode() + m2.mno;
		return Integer.compare(x, y);
		// return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}
}




