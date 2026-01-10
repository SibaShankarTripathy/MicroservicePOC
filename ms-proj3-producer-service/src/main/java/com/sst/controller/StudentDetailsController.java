package com.sst.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-api")
public class StudentDetailsController {
	
@Value("${server.port}")
String portNum;

@Value("${spring.application.name}")
String applicationName;

@Value("${eureka.instance.instance-id}")
String instanceId;

	// http://localhost:8762/student/getStudentDetails
	@GetMapping("/getStudentDetails")
	public ResponseEntity<String> getStudentDetails(){
		System.out.println("Welcome to Producer Service Controller Class.......");
		System.out.println("This service is running in this port:::::"+portNum);
		System.out.println("This service is running with this application name:::::"+applicationName);
		System.out.println("This service is running with this instanceId:::::"+instanceId);
		System.out.println("----------------------------------------------------------------------------");
		return new ResponseEntity<String>("This message from Producer Service output......",HttpStatus.OK);
	}
}
