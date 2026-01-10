package com.sst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.service.CollectDetailsService;

@RestController
@RequestMapping("/allDetails")
public class CollectDetailsController {
	
	// http://localhost:8763/allDetails/students
	@Autowired
	public CollectDetailsService collectDetailsService;
	
	@GetMapping("/students")
	public ResponseEntity<String> getStudentDetails(){
		System.out.println("This is Consumer Service Controller......");
		String studentDetails = collectDetailsService.getStudentDetails();
		return new ResponseEntity<String>(studentDetails, HttpStatus.OK);
	}

}
