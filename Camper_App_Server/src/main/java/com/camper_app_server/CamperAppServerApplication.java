package com.camper_app_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

public class CamperAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamperAppServerApplication.class, args);
	}

}
