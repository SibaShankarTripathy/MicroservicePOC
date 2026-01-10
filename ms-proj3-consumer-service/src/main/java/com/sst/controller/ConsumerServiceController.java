package com.sst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.service.ConsumerServiceImpl;

@RestController
@RequestMapping("/allDetails")
public class ConsumerServiceController {
	
	@Autowired
	public ConsumerServiceImpl consumerServiceImpl;
	// http://localhost:8763/allDetails/getAllDetails
	@GetMapping("getAllDetails")
	public ResponseEntity<String>getAllDetails(){
		System.out.println("Welcome to Consumer Service Controller Class.........");
		String fromProducerService = consumerServiceImpl.getStudentDetails();
		return new ResponseEntity<String>(fromProducerService, HttpStatus.OK);
	}
}
