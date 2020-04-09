package com.techfynder.forexengineservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ForexEngineServiceApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(ForexEngineServiceApplication.class, args);
	}

}
