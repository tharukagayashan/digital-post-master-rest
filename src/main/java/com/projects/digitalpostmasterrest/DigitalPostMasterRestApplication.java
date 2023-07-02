package com.projects.digitalpostmasterrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class DigitalPostMasterRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalPostMasterRestApplication.class, args);
	}

}
