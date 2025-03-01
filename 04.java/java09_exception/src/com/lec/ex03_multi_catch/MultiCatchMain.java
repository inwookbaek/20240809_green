package com.lec.ex03_multi_catch;

public class MultiCatchMain {

	public static void main(String[] args) {
		
		String data1 = null;
		String data2 = null;
		
		try {
			data1 = args[0];
			data2 = args[1];
			System.out.println("agrs.length = " + args.length);
			
			data1 = "100a";
			int val1 = Integer.parseInt(data1);
			double val2 = Double.parseDouble(data2);
			double result = val1 + val2;
//		} catch (Exception e) {
//			System.out.println("모든 예외는 부모예외로 형변환되어서 처리!!");
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("agrs.length = " + args.length + ", 실행매개변수가 부족합니다!!");
//			System.out.println("예외클래스이름 = " + e.getClass().getName());
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			return;
//		} catch (NumberFormatException e) {
//			System.out.println(data1 + " or " + data2 + "는 숫자로 변환할 수 없습니다!!");
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			return;
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("인덱스예외 or 숫자변환예외가 발생!!!");
		} finally {
			System.out.println("무조건처리되는 로직!!!");
		}
		
		System.out.println("아주 아주 중요한 로직을 실행!!");
	}

}
