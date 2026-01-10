package com.sst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl {
	
	// Injecting of proxy object in to Consumer Class
	@Autowired
	public ConsumerService consumerService;
	
	public String getStudentDetails() {
		System.out.println("Welcome to Consumer Service Impl Class.........");
		ResponseEntity<String> studentDetails = consumerService.getStudentDetails();
		String result = studentDetails.getBody().toString();
		return result;
	}
}
