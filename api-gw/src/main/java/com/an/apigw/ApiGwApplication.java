package com.an.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class ApiGwApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGwApplication.class, args);
	}
	
	@Bean
	public GlobalFilter customFilter() {
	    return new CustomGlobalFilter();
	}
}

@RestController
class FallbackController {

	@GetMapping("/savingsaccount-fallback")
	public ResponseEntity<String> getSavingsAccountFallback() {
		return ResponseEntity.status(504).body("Timeout occured");
	}
}
