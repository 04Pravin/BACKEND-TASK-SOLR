package com.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class StudentappCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentappCloudGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
		.route("studentService",ps->ps.path("/student-api/**").uri("lb://STUDENT-SERVICE:"))
		.route("studentViewService",ps->ps.path("/studentview-api/**").uri("lb://STUDENT-VIEW-SERVICE:"))
		.build();
	}

}
