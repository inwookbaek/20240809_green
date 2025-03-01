package com.lec.spmvc.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class SampleService {

//	@Autowired
//	private SampleDAO sampleDAO;
	
	@Qualifier("event")
	private final SampleDAO sampleDAO;
	
//	public SampleService() {
//		log.info("SampleService create..............");
//	}
//	
}
