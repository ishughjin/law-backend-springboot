package com.incypio.law.LegalConsultationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LegalConsultationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegalConsultationServiceApplication.class, args);
	}

}
