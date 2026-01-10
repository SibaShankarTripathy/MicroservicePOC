package com.sst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.service.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerServiceController {

	@Autowired
	public ConsumerService consumerService;
	
	@GetMapping("/consumerService")
	public ResponseEntity<String> getConsumerDetails(){
		String providerDetsils = consumerService.getProviderDetsils();
		return new ResponseEntity<>(providerDetsils,HttpStatus.OK);
	}
	
//This consumer service will communicate with another MicroService using Discovery Client. 
}
