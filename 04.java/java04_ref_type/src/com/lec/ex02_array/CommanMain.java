package com.lec.ex02_array;

public class CommanMain {

	public static void main(String[] scores) {
		// cmd창에서 java.exe HelloWorld 호추한 것처럼
		// 즉, 외부에서 실행할 경우 java.exe \경로\com.lec.ex02_array.CommanMain은
		// main메서드가 실행된다. 즉 Java에서 main메서드는 Application의 시작점이다.
		// main메서드호출할 때 전달되는 매개변수가 있을 경우에는 main(String[] args)
		// 에 정의된 것처럼 배열로 전달이 된다.
		// java.exe com.lec.ex02_array.CommanMain arg1 arg2 ... argn
		// main메서드의 매개변수는 only String배열로서만 전달이 가능하다.
		
		System.out.println("args.length = " + scores.length);
		
		int sum = 0;
		
		for(String score : scores) {
			int val = Integer.parseInt(score);
			sum += val;
		}
		System.out.println(String.format("scores배열의 합계 = %,d", sum));
		System.out.println(String.format("scores배열의 평균 = %,d", sum/scores.length));
		System.out.println();		
		
	}

}
