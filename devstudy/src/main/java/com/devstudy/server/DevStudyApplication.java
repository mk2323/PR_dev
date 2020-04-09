package com.devstudy.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DevStudyApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DevStudyApplication.class, args);
	}
	
	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DevStudyApplication.class);
	}

}
