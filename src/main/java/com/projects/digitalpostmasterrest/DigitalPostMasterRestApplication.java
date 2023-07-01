package com.projects.digitalpostmasterrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class DigitalPostMasterRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalPostMasterRestApplication.class, args);
	}

}
