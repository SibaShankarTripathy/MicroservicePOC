package com.sst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsProj3ConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProj3ConsumerServiceApplication.class, args);
	}

}
