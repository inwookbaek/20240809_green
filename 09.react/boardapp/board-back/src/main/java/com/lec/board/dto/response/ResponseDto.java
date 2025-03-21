package com.lec.board.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lec.board.common.ResponseCode;
import com.lec.board.common.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ResponseDto 클래스는 클라이언트에게 일관된 응답 형식을 제공하기 위한 데이터 전송 객체(DTO)입니다.
 * 
 * - code: 응답 코드 (예: "DATABASE_ERROR", "VALIDATION_FAILED" 등)
 * - message: 응답 메시지 (예: "데이터베이스 오류 발생", "유효성 검사 실패" 등)
 * 
 * 이 클래스를 사용하면 컨트롤러에서 예외 상황에 대한 일관된 응답을 쉽게 반환할 수 있습니다.
 */
@Getter // 모든 필드에 대한 Getter 메서드를 자동 생성 (Lombok 라이브러리)
@NoArgsConstructor // 기본 생성자 자동 생성 (매개변수 없는 생성자)
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 자동 생성
public class ResponseDto {
	
	private String code;    // 응답 코드 (예: "DATABASE_ERROR", "VALIDATION_FAILED")
	private String message; // 응답 메시지 (예: "데이터베이스 오류 발생", "유효성 검사 실패")

	/**
	 * 데이터베이스 오류가 발생했을 때 반환할 응답을 생성하는 정적 메서드입니다.
	 * 
	 * @return HTTP 상태 코드 500(INTERNAL_SERVER_ERROR)와 함께 ResponseDto 객체를 포함한 ResponseEntity
	 */
	public static ResponseEntity<ResponseDto> databaseError() {
		// 데이터베이스 오류에 대한 응답 DTO 객체 생성
		ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
		
		// HTTP 500 상태 코드와 함께 ResponseEntity 객체 반환
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
	}
	
	/**
	 * 유효성 검사 실패 시 반환할 응답을 생성하는 정적 메서드입니다.
	 * 
	 * @return HTTP 상태 코드 400(BAD_REQUEST)와 함께 ResponseDto 객체를 포함한 ResponseEntity
	 */
	public static ResponseEntity<ResponseDto> validationFailed() {
		// 유효성 검사 실패에 대한 응답 DTO 객체 생성
		ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAILED, ResponseMessage.VALIDATION_FAILED);
		
		// HTTP 400 상태 코드와 함께 ResponseEntity 객체 반환
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}
}
