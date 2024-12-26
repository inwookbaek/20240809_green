package com.lec.board.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
	// ModelMapper란? 서로 다른 클래스의 값을 한번에 복사하게 도와주는 라이브러리
	// 어떠한 Object에 있는 필드 값을 원하는 Object에 자동으로 mapping시켜주는 라이브러리이다.
	// 즉, Source Object의 값을 Destination Object에 자동으로 매핑해 준다고 생각하면 된다.
	
	@Bean // ModelMapper 스프링빈으로 설정
	public ModelMapper getModel() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration()
				   .setFieldMatchingEnabled(true)
				   .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				   .setMatchingStrategy(MatchingStrategies.LOOSE);
		
		/*
			1. MatchingStrategies.STANDARD : 기본값
			   
			   모든 destination객체의 property토큰들은 매칭되어야 한다.
			   모든 source객체의 property들은 하나 이상의 토큰이 매칭되어야 한다.
			   토큰은 어떤 순서로든 일치될 수 있다.
			
			2. MatchingStrategies.STRICT
			
			   가장 엄격한 전략으로 source와 destination의 타입과 필드명이 동일해야 한다.
			   동일할 때 변환이 된다. 의도하지 않은 매핑이 일어나는 것을 방지할 때 사용
			
			3. MatchingStrategies.LOOSE
			
			   가장 느슨한 전략을고 토큰을 어떤 순서로도 일치 시킬 수 있다. 마지막 
			   destination필드명은 모든 토큰이 일치해아 산다. 마지막 source필드명에는
			   일치하하는 토큰이 하나 이상 있어야 한다.
		*/
		
		return modelMapper;
	}
}
