package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
@EnableRetry
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.example")
@EnableJpaRepositories("com.example.repository")
@EntityScan(basePackages = "com.example.entity")

public class EcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcartApplication.class, args);
	}

}
