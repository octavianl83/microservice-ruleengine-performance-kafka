package com.logicore.rest.services.serviceprocessor3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@EnableFeignClients
@SpringBootApplication
public class ServiceProcessor3Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProcessor3Application.class, args);
	}

}
