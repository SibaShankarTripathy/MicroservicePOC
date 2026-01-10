package com.sst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsProj1ProviderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProj1ProviderServiceApplication.class, args);
	}

}
