package com.sst.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//In this class we will learn a simple process how to use GET, POST, DELETE, PUT and PATCH.

@RestController
@RequestMapping("/curdOperation") // Called Global Path
public class CurdOperation {

	@GetMapping("/getRec") // Use of GET
	// This method indicate to retrieve data
	public ResponseEntity<String> getRecords() {
		String recVal = "This is get Request method";
		return new ResponseEntity<String>(recVal, HttpStatus.OK); // Status will 200
	}

	@PostMapping("/insrtRec") // Use of POST
	// This method indicate to insert data.
	public ResponseEntity<String> postRecords() {
		String recVal = "This is Post Request method";
		return new ResponseEntity<String>(recVal, HttpStatus.CREATED); // Status will 204
	}

	@PutMapping("/udateRec")
	// This method indicate to update full data.
	public ResponseEntity<String> updateRecords() {
		String recVal = "This is Update Request method";
		return new ResponseEntity<String>(recVal, HttpStatus.OK); // Status will 200
	}

	@PatchMapping("/editRec")
	// This method indicate to update partial data NOT full record.
	public ResponseEntity<String> editRecords() {
		String recVal = "This is Patch Request method";
		return new ResponseEntity<String>(recVal, HttpStatus.OK); // Status will 200
	}

	@DeleteMapping("/delRec")
	// This method indicate to delete data.
	public ResponseEntity<String> deleteRecords() {
		String recVal = "This is Delete Request method";
		return new ResponseEntity<String>(recVal, HttpStatus.OK); // Status will 200
	}
}
