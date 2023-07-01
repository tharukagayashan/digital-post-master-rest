package com.projects.digitalpostmasterrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@ComponentScan(basePackages = "com.projects.digitalpostmasterrest.dao")
@SpringBootConfiguration
@SpringBootApplication
public class DigitalPostMasterRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalPostMasterRestApplication.class, args);
	}

}
