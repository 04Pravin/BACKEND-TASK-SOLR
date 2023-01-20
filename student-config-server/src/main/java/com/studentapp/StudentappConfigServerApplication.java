package com.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class StudentappConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentappConfigServerApplication.class, args);
	}

}
