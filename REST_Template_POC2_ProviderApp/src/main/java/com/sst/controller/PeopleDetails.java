package com.sst.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.mode.PeoplesData;

@RestController
@RequestMapping("/peopleDetails")
public class PeopleDetails {
	
	@PostMapping("/saveDetails")
	public ResponseEntity<String> savePeopleDetails(@RequestBody PeoplesData peoplesData){
		System.out.println("===== Welcome to Provider App ======");
		System.out.println("Request data from consumer application:::: "+peoplesData);
		System.out.println("=============================================================");
		// Here I am not processing anything on request data from consumer application.
		// For demo purpose just added some value in object and will send to consumer application in String format.
		PeoplesData pepData = new PeoplesData("Mike",52,789654,"Hyderabad");
		return new ResponseEntity<String>(pepData.toString(), HttpStatus.CREATED);
	}

}
