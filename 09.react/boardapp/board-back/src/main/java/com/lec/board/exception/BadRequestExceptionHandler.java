package com.lec.board.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lec.board.dto.response.ResponseDto;

/**
 * BadRequestExceptionHandler 클래스는 클라이언트의 잘못된 요청(Bad Request)으로 인해 발생하는 예외를 처리하는 클래스입니다.
 * 
 * - @RestControllerAdvice: 컨트롤러 전역에서 발생하는 예외를 처리하는 역할을 합니다.
 * - @ExceptionHandler: 특정 예외가 발생했을 때 실행되는 메서드를 지정합니다.
 * 
 * 🔹 핵심 개념
 * @RestControllerAdvice란?
 * 
 * 모든 컨트롤러에서 발생하는 예외를 전역적으로 처리할 수 있도록 도와주는 Spring의 기능
 * 개별 컨트롤러마다 try-catch를 사용하지 않고도 예외를 통합 관리할 수 있음
 * @ExceptionHandler란?
 * 
 * 특정 예외가 발생하면 자동으로 실행되는 메서드
 * {MethodArgumentNotValidException.class, HttpMessageNotReadableException.class}
 * → 두 개의 예외가 발생하면 validationExceptionHandler()가 실행됨
 * 
 * 처리하는 예외 종류

 * MethodArgumentNotValidException:
 * 요청 데이터가 유효성 검사(예: @NotNull, @Size)를 통과하지 못했을 때 발생
 * HttpMessageNotReadableException:
 * 요청 데이터가 JSON 포맷이 잘못되었거나 매핑할 수 없는 경우 발생
 * 응답 방식
 * 
 * ResponseDto.validationFailed()를 호출하여 ResponseEntity<ResponseDto> 객체를 반환
 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody)
 * → HTTP 400 상태 코드와 응답 본문을 함께 반환
 */
@RestControllerAdvice // 컨트롤러 전역에서 예외를 감지하고 적절한 응답을 반환하는 역할
public class BadRequestExceptionHandler {

    /**
     * 클라이언트 요청 데이터가 잘못되었을 때 발생하는 예외를 처리하는 메서드입니다.
     * 
     * @param exception 예외 객체 (MethodArgumentNotValidException 또는 HttpMessageNotReadableException)
     * @return HTTP 상태 코드 400(BAD_REQUEST)와 함께 ResponseDto 객체를 포함한 ResponseEntity
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseDto> validationExceptionHandler(Exception exception) {
        // ResponseDto의 validationFailed() 메서드를 호출하여 유효성 검사 실패 응답을 생성 후 반환
        return ResponseDto.validationFailed();
    }
}
