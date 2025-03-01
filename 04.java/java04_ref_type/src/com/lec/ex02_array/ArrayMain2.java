package com.lec.ex02_array;

public class ArrayMain2 {

	public static void main(String[] args) {
		// 2. 배열의 선언과 초기화 - new 연산자
		// int[] scores = {90,95,88,90,89,100,99,78,66,65,88,99};
		int scores[];  // 선언만되어 있고, 크기와 값은 미정
		// System.out.println(scores[0]); // 에러, 초기화가 않되어 있다.
		scores = new int[] {90,95,88,90,89,100,99,78,66,65,88,99};

		int sum = 0;
		for(int score : scores) {
			sum += score;
		}
		System.out.println(String.format("scores배열의 합계 = %,d", sum));
		System.out.println(String.format("scores배열의 평균 = %,d", sum/scores.length));
		System.out.println();
		
		// 3. 메서드를 이용한 배열처리
		int tot = total(scores);
		System.out.println(String.format("scores배열의 합계 = %,d", tot));
		System.out.println(String.format("scores배열의 평균 = %,d", tot/scores.length));
	}

	private static int total(int[] scores) {
		int sum = 0;
		for(int score : scores) {
			sum += score;
		}
		return sum;
	}

}















