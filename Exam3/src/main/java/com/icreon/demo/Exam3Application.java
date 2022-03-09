package com.icreon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
public class Exam3Application {

	public static void main(String[] args) {
		SpringApplication.run(Exam3Application.class, args);
	}

}
