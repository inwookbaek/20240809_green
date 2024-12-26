package com.lec.board.security.exception;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.http.MediaType;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletResponse;

public class AccessTokenException extends RuntimeException {

	TOKEN_ERROR token_error;
	
	// 에러타입(enum)
	public enum TOKEN_ERROR {
		UNACCEPT(401, "토큰이 널이거나 길이가 짧다!!"),
		BADTYPE(401, "토큰타입(Bearer)이 잘못 되었습니다!!"),
		MALFORM(403, "토큰형식이 잘못되었습니다!!"),
		BADSIGN(403, "잘못된 서명입니다!!"),
		// EXPIRED(403, "토큰의 유효기간이 초과 되었습니다!!");
		EXPIRED(403, "Expired Token");
		
		private int status;
		private String msg;
		
		TOKEN_ERROR(int status, String msg) {
			this.status = status;
			this.msg = msg;
		}

		public int getStatus() {
			return this.status;
		}

		public String getMsg() {
			return this.msg;
		}
	}
	
	// 생성자
	public AccessTokenException(TOKEN_ERROR error) {
		super(error.name());
		this.token_error = error;
	}
	
	// 메시지전달메서드
	public void sendResponseError(HttpServletResponse response) {
		
		response.setStatus(token_error.getStatus());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		Gson gson = new Gson();
		
		String responseStr = gson.toJson(Map.of("msg", token_error.msg, "time", new Date()));
		
		try {
			response.getWriter().println(responseStr);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
} 
