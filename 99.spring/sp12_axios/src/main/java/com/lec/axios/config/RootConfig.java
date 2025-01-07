package com.lec.axios.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
	// ModelMapper란? 서로 다른 클래스의 값을 한번에 복사하게 도와주는 라이브러리
	// 어떠한 Object에 있는 필드 값들을 원하는 Object에 자동으로 mapping 시켜주는 라이브러리이다.
	// 즉, Source Object → Destination Object 를 자동으로 해준다고 생각하면 된다.
	
	@Bean // ModelMapper를 스프링빈으로 설정
	public ModelMapper getModel() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                /* 
	                1. MatchingStrategies.STANDARD(default)
						모든 destination 객체의 property 토큰들은 매칭 되어야 한다.
						모든 source 객체의 property들은 하나 이상의 토큰이 매칭되어야 한다.
						토큰은 어떤 순서로든 일치될 수 있다.
					
					2. MatchingStrategies.STRICT
						가장 엄격한 전략
						source와 destination의 타입과 필드명이 같을 때만 변환
						의도하지 않은 매핑이 일어나는 것을 방지할 때 사용
					3. MatchingStrategies.LOOSE
						가장 느슨한 전략
						토큰을 어떤 순서로도 일치 시킬 수 있다.
						마지막 destination 필드명은 모든 토큰이 일치해야 한다.
						마지막 source 필드명에는 일치하는 토큰이 하나 이상 있어야 한다.
                 */
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper;
	}
}
