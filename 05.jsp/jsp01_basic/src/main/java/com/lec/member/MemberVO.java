package com.lec.member;

import java.util.Date;

/*
	DTO(VO, JavaBeans)
	
	자바빈은 속성(데이터), 변경이벤트, 객체의 직렬화를 위한 표준이다.
	자바빈규약에 따르는 자바 클래스를 JavaBean이라고 한다.
	자바빈 클래스느 데이터를 저장하는 필드, 데이터를 읽어 올 때 사용
	되는 메서드 그리고 값을 저장할 때 사용되는 메서드로 구성된다.
	
	자바빈프로퍼티
	
	프로퍼티는 자바빈에 저장되는 값을 나타낸다. 메서드 이름을 사용해서
	프로퍼티의 이름을 결정하게 된다. 프로퍼티의 값을 변경하는 메서드는
	프로퍼티이름 중 첫 글자를 대문다로 변환한 문자열 앞에 set을 붙이고
	값을 읽어오는 메서드은 프로퍼티의 이름중 첫글자를 대문자로 변환한후
	get을 붙인다.
	
	읽기전용 프로퍼티  : get or is메서드만 존재하는 프로퍼티
	읽기/쓰기 프로퍼티 : get/set or is/set메서드가 존재하는 프로퍼티
*/
public class MemberVO {

	private String id;
	private String password;
	private String name;
	private Date regDate;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}












