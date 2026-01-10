package com.sst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsProj2ConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProj2ConsumerServiceApplication.class, args);
	}

}
