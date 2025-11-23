package com.sst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsPoc1EurekaBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPoc1EurekaBasicApplication.class, args);
	}

}
