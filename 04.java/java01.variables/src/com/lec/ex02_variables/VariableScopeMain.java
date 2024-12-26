package com.lec.ex02_variables;

public class VariableScopeMain {

	public static void main(String[] args) {
		// 변수의 사용범위
		// local variable vs global variable(지역변수 vs 전역변수)
		int var1 = 10;
		System.out.println("var1 = " + var1);
		System.out.println();
		
		if(true) {
			int var2;   // 지역변수 즉, if블럭안에서 선언된 변수이기 때문에 if블럭안에서만 사용가능
			var1 = 20;  // 전역변수 즉, main블럭안에서 사용가능
			var2 = 20;  
			System.out.println("var1 = " + var1);
			System.out.println("var2 = " + var2);
		}
		System.out.println();
		
		// System.out.println("var2 = " + var2); 
		// var2는 if블럭안에서만 사용가능한 지역변수이기 때문에 
		// if블럭을 밖에서는 사용할 수 없다. 

		if(true) {
			int var3;
			var1 = 30;
			// var2 = 30;   // var2는 첫 번째 if블럭안에서 사용가능한 지역변수
			int var2 = 30;  // 여기서의 var2는 현 if블럭안에서 사용가능한 지역변수
			var3 = 30;
			System.out.println("var1 = " + var1);
			System.out.println("두 번째 if블럭의 지역변수 var2 = " + var2);			
			System.out.println("var3 = " + var3);
		}
		System.out.println();
		
		System.out.println("var1 = " + var1);
		// System.out.println("var2 = " + var2);			
		// System.out.println("var3 = " + var3);			
	}
}
