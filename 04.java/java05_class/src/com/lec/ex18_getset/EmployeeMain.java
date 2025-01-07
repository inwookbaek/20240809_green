package com.lec.ex18_getset;

import java.util.Date;

public class EmployeeMain {

	public static void main(String[] args) {
		Employee emp = new Employee();
		
		emp.setEmpno(7369);
		System.out.println("사원번호 = " + emp.getEmpno());
		
		emp.setAdult(false);
		System.out.println(emp.isAdult() ? "성인입니다!" : "미성년자입니다!");
		
		emp.set질병(false);
		System.out.println(emp.is질병() ? "질병이 있습니다! ㅠㅠ" : "건강하시네요! ㅎㅎ");
		System.out.println();
		
		Employee emp1 = new Employee(1000, "홍길동", new Date(), 10000, 10, true, "991118-1234567");
		Employee emp2 = new Employee(1001, "홍길순", new Date(), 15000, 20, true, "991118-2234567");
		Employee emp3 = new Employee(1002, "홍길자", new Date(), 13000, 30, true, "991118-3234567");
		System.out.println(emp1.hashCode());
		System.out.println(emp2.hashCode());
		System.out.println(emp3.hashCode());
		System.out.println();
		
		System.out.println(emp1.toString());
		System.out.println(emp2.toString());
		System.out.println(emp3.toString());
		System.out.println();
		
		String[] names = {"홍길동", "손흥민", "소향"};
		
		Employee[] employees = {emp1, emp2, emp3, new Employee()
				, new Employee(1004, "홍길상", new Date(), 125000, 40, true, "991118-4234567")};
		
		for(Employee employee : employees) {
			System.out.println(employee.toString());
		}
		
	}
}