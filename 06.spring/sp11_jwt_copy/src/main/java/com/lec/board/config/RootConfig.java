package com.lec.board.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class RootConfig {
	
	@Bean 
	public ModelMapper getMepper() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration()
				   .setFieldMatchingEnabled(true)
				   .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				   .setMatchingStrategy(MatchingStrategies.LOOSE);
			
		return modelMapper;
	}
}
