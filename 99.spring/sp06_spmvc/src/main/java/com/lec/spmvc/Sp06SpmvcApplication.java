package com.lec.spmvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lec.apmvc.mapper")
public class Sp06SpmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp06SpmvcApplication.class, args);
	}

}
