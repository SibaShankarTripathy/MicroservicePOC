package com.sst.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentDetails {
	// This section for extra content like how to create multiple instance of specific service manually.
	
	@Value("${spring.application.name}")
	String applicationName;
	
	@Value("${server.port}")
	String serverPort;
	
	@Value("${eureka.instance.instance-id}")
	String instanceName;
	
	

	//http://localhost:8762/students/personalDetails
	@GetMapping("/personalDetails")
	public ResponseEntity<String> getStudentDetails(){
		System.out.println("This is Producer Service.......");
		System.out.println("Currently this service is running in this port number::::"+serverPort+" and application name is::::"+applicationName);
		System.out.println("Current service Instance ID:::::::"+instanceName);
		return new ResponseEntity<String>("Welcome to Student Details Portal of Producer Service", HttpStatus.OK);
	}
}
