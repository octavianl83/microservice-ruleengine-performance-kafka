package com.logicore.rest.services.serviceprocessor2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@EnableFeignClients
@SpringBootApplication
@EnableKafka
public class ServiceProcessor2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProcessor2Application.class, args);
	}

}
