package com.lec.service;

public class ServiceException extends RuntimeException {

	public ServiceException(String error) {
		super(error);
	}
	
	public ServiceException(String error, Exception cause) {
		super(error, cause);
	}
}
