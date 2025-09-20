package com.sst.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sst.crud.entity.StudentDetailsTab;
import com.sst.crud.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

// In this class we will learn basic REST API design.
// REST API nothing but a request form client taken by REST controller then process on that in service layer and return a response to client.

	@Autowired
	private StudentService studentService;

	// API/URL: http://localhost:8092/student/saveDetails
	@PostMapping("/saveDetails")
	public ResponseEntity<?> saveStudentDetails(@RequestBody StudentDetailsTab studentDetails) {
		String registerStudentsMsg = studentService.registerStudents(studentDetails);
		if (registerStudentsMsg != null && !registerStudentsMsg.isEmpty())
			return new ResponseEntity<>(registerStudentsMsg, HttpStatus.CREATED);
		return new ResponseEntity<>("Error in saving record please try again", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// API/URL: http://localhost:8092/student/getStudentDetails?stdSerialId=1001
	@GetMapping("/getStudentDetails")
	public ResponseEntity<?> getStudentDetailsById(@RequestParam() Long stdSerialId) {
		StudentDetailsTab studentdetailsById = studentService.getStudentdetailsById(stdSerialId);
		if (studentdetailsById != null)
			return new ResponseEntity<StudentDetailsTab>(studentdetailsById, HttpStatus.FOUND);
		return new ResponseEntity<String>("No Data Found, enter Student ID correctly", HttpStatus.FOUND);
	}

	// URL: http://127.0.0.1:8092/student/getStdByAge/25
	@GetMapping("/getStdByAge/{stdAge}")
	public ResponseEntity<?> getStudentListByAge(@PathVariable Integer stdAge) {
		List<StudentDetailsTab> studentListByAge = studentService.getStudentListByAge(stdAge);
		// Sorted the list of student
		if (studentListByAge != null && !studentListByAge.isEmpty()) {
			studentListByAge.sort((age1, age2) -> age1.getStdId().compareTo(age2.getStdId()));
			return new ResponseEntity<List<StudentDetailsTab>>(studentListByAge, HttpStatus.OK);
		}
		return new ResponseEntity<String>("No student details Found at this age::::"+stdAge, HttpStatus.BAD_REQUEST);
	}

	// This below method will update partially to record
	// URL: http://localhost:8092/student/updateStudentById/1006/Goa/8458796235
	@PatchMapping("/updateStudentById/{stdId}/{stdAddr}/{stdPhone}")
	public ResponseEntity<?> updateStudentDetailsById(@PathVariable Long stdId, @PathVariable String stdAddr,
			@PathVariable Long stdPhone) {
		StudentDetailsTab studentDetails = studentService.updateStudentDetailsById(stdId, stdAddr, stdPhone);
		if (studentDetails != null)
			return new ResponseEntity<StudentDetailsTab>(studentDetails, HttpStatus.OK);

		return new ResponseEntity<String>("Student detils NOT updated either no data found or bad input", HttpStatus.NOT_FOUND);

	}
	
	// This below method will update fully of existing record
	// URL: http://localhost:8092/student/updateFullStudentDetailsById
	@PutMapping("/updateFullStudentDetailsById")
	public ResponseEntity<?> updateFullStudentDetailsById(@RequestBody StudentDetailsTab studentDetails) {
		StudentDetailsTab updateStudentDetails = studentService.updateFullStudentDetailsById(studentDetails);
		if (updateStudentDetails != null)
			return new ResponseEntity<StudentDetailsTab>(updateStudentDetails, HttpStatus.OK);
		return new ResponseEntity<String>("Student detils NOT updated either no data found or bad input",  HttpStatus.NOT_FOUND);
	}
	//URL: http://localhost:8092/student/deleteStudent/1007
	@DeleteMapping("/deleteStudent/{stdId}")
	public ResponseEntity<String> deleteStudentdetailsById(@PathVariable Long stdId){
		String deleteStatus = "";
		deleteStatus = studentService.deleteStudentdetailsById(stdId);
		if(deleteStatus != null && !deleteStatus.isEmpty())
			return new ResponseEntity<String>(deleteStatus,HttpStatus.OK);
		return new ResponseEntity<String>("Error while delete record",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

/*
 * Basic request JSON format you can hit from POSTMAN tool while first insert
 * {
    "stdRlNo": "AN01",
    "stdName": "AnilKumar",
    "stdClass": "STD_3",
    "stdAddress": "Rajstan",
    "stdDOB": "2008-02-25",
    "stdPhone": 8523695234,
    "stdFees": 18000
	}
 */

/*
 * Basic request JSON format you can hit from POSTMAN tool while fully update existing record. stdId is mandatory field to update so added here
 * {
    "stdId":1006,
    "stdRlNo": "AN02",
    "stdName": "AnilKumar",
    "stdClass": "STD_4",
    "stdAddress": "Manipur",
    "stdDOB": "2010-03-15",
    "stdPhone": 8529512234,
    "stdFees": 19000
	}
  */
