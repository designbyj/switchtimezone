package com.designbyj.springboot.timestamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {
		"com.designbyj.springboot.timestamp"})
@SpringBootApplication
public class TimestampApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimestampApplication.class, args);

	}

}


