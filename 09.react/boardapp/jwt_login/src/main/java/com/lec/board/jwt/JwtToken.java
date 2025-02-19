package com.lec.board.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
/*
  	클라이언트에 토큰을 보내기 위해 JwtToken DTO를 생성한다
	JwtToken의 필드 중  grantType는 JWT에 대한 인증 타입이다.
	단순하고 직관적이며 널리 사용되는 "Bearer" 인증 방식을 사용할 것이다. 
	이 인증 방식은 Access Token을 HTTP 요청의 Authorization 헤더에 포함하여 전송한다. 
*/
@Builder
@Data
@AllArgsConstructor
public class JwtToken {
	
    private String grantType;
    private String accessToken;
    private String refreshToken;
    
}
