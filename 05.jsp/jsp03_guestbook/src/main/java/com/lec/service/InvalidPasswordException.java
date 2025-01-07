package com.lec.service;

public class InvalidPasswordException extends ServiceException {

	public InvalidPasswordException(String error) {
		super(error);
	}
}
