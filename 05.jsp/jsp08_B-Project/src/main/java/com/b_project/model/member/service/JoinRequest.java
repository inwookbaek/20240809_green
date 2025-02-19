package com.b_project.model.member.service;

import java.util.Map;

public class JoinRequest {

	private String id;
	private String name;
	private String password;
	private String confirmPassword;
	private String tel;
	private String eMail;
	
	
	public JoinRequest(String id, String name, String password, String confirmPassword, String tel, String eMail) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.tel = tel;
		this.eMail = eMail;
	}
	
	
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public String getTel() {
		return tel;
	}
	public String geteMail() {
		return eMail;
	}
	
	
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, id, "id");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPassword, "confirmPassword");
		if(!errors.containsKey("confirmPassword")) {
			if(!isPasswordEqualToConfirm()) {
				errors.put("noMatch", Boolean.TRUE);
			}
		}
		checkEmpty(errors, eMail, "eMail");
//		id가 숫자로 시작하면 에러 발생. Kakao api로 회원가입하는 사람들 아이디가 다 숫자로 되어있기 때문에 중복을 피하기 위해서
		if(idStartsWithNumber()) {
			errors.put("startWithNumber", Boolean.TRUE);
		}
	}
	
	private boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPassword);
	}
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) errors.put(fieldName, Boolean.TRUE);
	}
	private boolean idStartsWithNumber() {
		boolean number = false;
		
		for(int i=0; i<10; i++) {
			number = id.startsWith(i + "");
			if(number) break;
		}
		return number;
	}
}
