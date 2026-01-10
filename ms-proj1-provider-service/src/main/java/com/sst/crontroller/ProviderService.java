package com.sst.crontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderService {
	
	@Value("${spring.application.name}")
	String appName;
	
	@Value("${server.port}")
	String serverPort;

	@GetMapping("/providerDetails")
	public ResponseEntity<String> getProviderDetails(){
		System.out.println("==== Welcome to Provider Service ====");
		return new ResponseEntity<String>("This is "+appName+ " controller class it is running in port:: "+ serverPort, HttpStatus.OK);
	}
}
// To understand the Discovery Client go to Consumer Service