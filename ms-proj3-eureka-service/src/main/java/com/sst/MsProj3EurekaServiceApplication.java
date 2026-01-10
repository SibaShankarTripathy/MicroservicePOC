package com.sst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsProj3EurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProj3EurekaServiceApplication.class, args);
	}

}
