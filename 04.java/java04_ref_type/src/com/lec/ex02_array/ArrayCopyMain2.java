package com.lec.ex02_array;

import java.util.Arrays;

/*
	배열의 복사
	
	참조타입인 배열인 경우, 배열복사가 되면 복사되는 값이 객체의 메모리주소이기 때문에
	새 배열의 항목은 이전 배열이 참조하는 객체의 메모리주소와 동일하다.
	
	이 것을 얕은 복사(shallow copy)라고 한다. 반대로 깊은 복사(deep copy)는 참조하는 
	객체를 별도로 생성해서 복사하는 것을 말한다.
	
	Array복사
	
	자바에서는 배열을 한번 생성하면 크기를 변경할 수 없기 때문에 더 많은 요소를 저장하려면
	보다 큰 배열을 새로 만들고 이전 배열에서 항목값들을 복사해야 한다.
	배열간 항목을 복사하려면 for문을 이용하거나 System.arrayCopy(), Arrays.copyOf()메서드를
	사용해서 깊은 복사를 하게 한다.
*/
public class ArrayCopyMain2 {

	public static void main(String[] args) {
		// 3. 배열의 복사 - 깊은 복사
		
		// 1) 얕은 복사
		String[][] names1 = {{"거미", "나얼"}, {"소향", "손흥민", "이강인"}};		
		String[][] names2 = names1;
		System.out.println("names1.hashCode = " + names1.hashCode());
		System.out.println();
		
		System.out.println("names1[0].hashCode = " + names1[0].hashCode());
		System.out.println("names1[0][0].hashCode(거미) = " + names1[0][0].hashCode());
		System.out.println("names1[0][1].hashCode(나얼) = " + names1[0][1].hashCode());
		System.out.println();
		
		System.out.println("names1[1].hashCode = " + names1[1].hashCode());
		System.out.println("names1[1][0].hashCode(소향) = " + names1[1][0].hashCode());
		System.out.println("names1[1][1].hashCode(손흥민) = " + names1[1][1].hashCode());
		System.out.println("names1[1][2].hashCode(이강인) = " + names1[1][2].hashCode());
		System.out.println("-".repeat(50));	
		
		System.out.println("names2[0].hashCode = " + names2[0].hashCode());
		System.out.println("names2[0][0].hashCode(거미) = " + names2[0][0].hashCode());
		System.out.println("names2[0][1].hashCode(나얼) = " + names2[0][1].hashCode());
		System.out.println();
		
		System.out.println("names2[1].hashCode = " + names2[1].hashCode());
		System.out.println("names2[1][0].hashCode(소향) = " + names2[1][0].hashCode());
		System.out.println("names2[1][1].hashCode(손흥민) = " + names2[1][1].hashCode());
		System.out.println("names2[1][2].hashCode(이강인) = " + names2[1][2].hashCode());		
		System.out.println("-".repeat(50));	
		
		// 2) 깊은 복사 - for문
		String[] old1 = {"거미", "나얼", "소향"};
		String[] new1 = new String[3];
		System.out.println("old1.hashCode = " + old1.hashCode());
		System.out.println("new1.hashCode = " + new1.hashCode());
		System.out.println("old1[0].hashCode(거미) = " + old1[0].hashCode());
		System.out.println("old1[1].hashCode(나얼) = " + old1[1].hashCode());
		System.out.println("old1[2].hashCode(소향) = " + old1[2].hashCode());
		System.out.println();
		
		for(int i=0;i<old1.length;i++) {
			new1[i] = old1[i];
		}
		
		old1[0] = "손흥민";  // 거미 -> 손흥민
		old1[2] = "김민재";  // 소향 -> 김민재	
		System.out.print("old1 = {");
		for(String old : old1) {
			System.out.print(old + "(" + old.hashCode() + "),");
		}
		System.out.print("}\n");
		
		System.out.print("new1 = {");
		for(String n : new1) {
			System.out.print(n + "(" + n.hashCode() + "),");
		}
		System.out.print("}\n");
		System.out.println("-".repeat(50)+"\n");		
		
		// 3) 깊은 복사 - System.arraycopy(old, 시작, new, new시작, old크기)
		String[] old2 = {"거미", "나얼", "소향"};
		String[] new2 = new String[6];		
		System.arraycopy(old2, 0, new2, 0, old2.length);
		System.out.print("old2 = {");
		for(String old : old2) {
			System.out.print(old + "(" + old.hashCode() + "),");
		}
		System.out.print("}\n");
		
		new2[0] = "황인범";
		new2[3] = "BTS";
		new2[4] = "블랙핑크";
		new2[5] = "뉴진스";
		
		System.out.print("new2 = {");
		for(String n : new2) {
			System.out.print(n + "(" + n.hashCode() + "),");
		}	
		System.out.print("}\n" + "-".repeat(50)+"\n");
		
		// 4) 깊은 복사 - Arrays.copyOf(src, new.length)
		int[] old3 = {1,2,3,4,5};
		int[] new3 = Arrays.copyOf(old3, 3);
		System.out.println("new3.length = " + new3.length);
		System.out.print("old3 = {");
		for(int old : old3) {
			System.out.print(old + ",");
		}
		System.out.print("}\n");		
		System.out.print("new3 = {");
		for(int n : new3) {
			System.out.print(n + ",");
		}
		System.out.print("}\n" + "-".repeat(50)+"\n");		
				
		// 5) 깊은 복사 - Arrays.copyOfRange(src, start. end-1);
		int[] new4 = Arrays.copyOfRange(old3, 1, 4);
		System.out.print("new4 = {");
		for(int n : new4) {
			System.out.print(n + ",");
		}
		System.out.print("}\n" + "-".repeat(50)+"\n");	
	}
}