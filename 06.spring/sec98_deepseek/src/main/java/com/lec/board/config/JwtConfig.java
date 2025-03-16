package com.lec.board.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
// 외부 설정 파일(application.properties 또는 application.yml)에서 jwt로 시작하는 설정 값을 이 클래스에 자동으로 매핑
// application.properties
// jwt:
//   secret: DdqOm0p+pbVNqhj12jafRAnzF62IyfOSFbzeIjTWvLY=    # 키생성 : openssl rand -base64 32
//   expiration: 86400000                                    # ms : 60*60*60*24 = 24시간
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
    private long expiration;
}
