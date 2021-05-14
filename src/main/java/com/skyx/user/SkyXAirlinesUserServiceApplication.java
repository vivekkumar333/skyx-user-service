package com.skyx.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class SkyXAirlinesUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyXAirlinesUserServiceApplication.class, args);
	}

}
