package com.officedepot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:appConfig.xml")
public class PriceFetcherMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceFetcherMicroserviceApplication.class, args);
	}
}
