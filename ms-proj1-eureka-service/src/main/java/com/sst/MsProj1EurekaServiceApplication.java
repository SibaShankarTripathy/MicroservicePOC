package com.sst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsProj1EurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProj1EurekaServiceApplication.class, args);
	}

}
