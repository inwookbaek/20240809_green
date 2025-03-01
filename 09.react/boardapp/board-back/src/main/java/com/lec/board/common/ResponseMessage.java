package com.lec.board.common;

public interface ResponseMessage {

	// HTTP Status 200
	String SUCCESS = "Success";
	
	// HTTP Status 400
	String VALIDATION_FAILED = "Validation Fail!!";
	String DUPLICATE_EMAIL = "Duplicate Email!!";
	String DUPLICATE_NICKNAME = "Duplicate Nickname!!";
	String DUPLICATE_TEIL_NUMBER = "Duplicate Telphone Number!!";
	String NOT_EXISTED_USER = "This user dose not exit.";
	String NOT_EXISTED_BOARD = "This board does not exit.";
	
	// HTTP Status 401	
	String SIGN_IN_FAIL = "Login information mismatch";
	String AUTHORIZATION_FAIL = "AUthorization Failed!!";

	// HTTP Status 403
	String NO_PERMISSION = "Do not have permission!!";
	
	// HTTP Status 500
	String DATABASE_ERROR = "Database Error!!";
	
}
