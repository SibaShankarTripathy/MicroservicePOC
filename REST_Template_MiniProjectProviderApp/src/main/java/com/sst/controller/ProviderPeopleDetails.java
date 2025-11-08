package com.sst.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sst.model.PeopleDetails;

@RestController
@RequestMapping("peopleDetails")
public class ProviderPeopleDetails {

	// In this below method I/P -- ObjectBody O/P -- String
	@PostMapping("/saveDetails")
	public ResponseEntity<String> savePeopleDetails(@RequestBody PeopleDetails peopleDetails) {
		System.out.println("============= This is Provider App people save method =============");
		System.out.println("People details from consmer app for saving::::" + peopleDetails);
		// Here I am NOT processing anything on consumer send data for demo purpose, all
		// hard code value.
		// Just I am sending a confirmation message to consumer app.
		String responseToConsumer = peopleDetails.getPplName() + " details saved successfully.....";
		return new ResponseEntity<String>(responseToConsumer, HttpStatus.CREATED);
	}

	// In this below method I/P -- SingleParameter O/P -- String
	@GetMapping("/getPplDtlsByPhn")
	public ResponseEntity<String> getPeopleDetailsByPhnNum(@RequestParam Long phoneNum, @RequestParam Integer pplAge) {
		System.out.println("============= This is Provider get people details by phone number method =============");
		System.out.println("Consumer App requested phone number::::" + phoneNum);
		System.out.println("Consumer App requested age::::" + pplAge);
		
		PeopleDetails pd = new PeopleDetails("Mike", "Hyderabad", 22, 1234567890L);
		return new ResponseEntity<String>(pd.toString(), HttpStatus.OK);
	}

	// In this below method I/P -- MultiParameter O/P -- String
	@GetMapping("/getPplAddrs/{name}/{phone}")
	public ResponseEntity<String> getPeopleAddrByPhnAndNme(@PathVariable String name, @PathVariable Long phone) {
		System.out.println("============= This is Provider Application's get address method =============");
		System.out.println("Name and Phone number from consumer app::::" + name + "----" + phone);
		// Here I am not processing anything on consumer request details
		String peopleName = "Jick";
		return new ResponseEntity<String>(peopleName, HttpStatus.OK);
	}

	// http://localhost:8081/RESTTemplateMiniProject/peopleDetails/getAllPeopleDetails
	// In this below method I/P -- nothing O/P -- ListObject
	@GetMapping("/getAllPeopleDetails")
	public ResponseEntity<?> getAllPeopledetails() {
		System.out.println("============= This is Provider get all people details method =============");
		// Here I will create some dummy data to send consumer app.
		PeopleDetails pd1 = new PeopleDetails("Jick", "Boston", 24, 7896541230L);
		PeopleDetails pd2 = new PeopleDetails("Jhon", "NewWork", 29, 951235784L);
		PeopleDetails pd3 = new PeopleDetails("Jimmy", "LosAngels", 32, 7532159874L);
		PeopleDetails pd4 = new PeopleDetails("Lucy", "London", 23, 8523697412L);
		List<PeopleDetails> allPeopleDetails = List.of(pd1, pd2, pd3, pd4);
		System.out.println("PeopleDetails set in list inside provider app::::" + allPeopleDetails.toString());
		return new ResponseEntity<List<PeopleDetails>>(allPeopleDetails, HttpStatus.OK);
	}
	
	// In this below method I/P -- nothing O/P -- ListObject
	@GetMapping("/getSinglePeopleDetails")
	public ResponseEntity<?> getSinglePeopledetails() {
		System.out.println("============= This is Provider get single people details method =============");
		// Here I will create some dummy data to send consumer app.
		PeopleDetails pd1 = new PeopleDetails("Jick", "Boston", 24, 7896541230L);
		System.out.println("PeopleDetails set in list inside provider app::::" + pd1.toString());
		return new ResponseEntity<PeopleDetails>(pd1, HttpStatus.OK);
	}
	
	// In this below method I/P -- nothing O/P -- ListObject
	@GetMapping("/getSinglePeopleDetailsObjFrmt")
	public ResponseEntity<?> getSinglePeopleDetailsObjFrmt() {
		System.out.println("============= This is Provider get single people details method Object to JSON =============");
		// Here I will create some dummy data to send consumer app.
		PeopleDetails pd1 = new PeopleDetails("Jick", "Boston", 24, 7896541230L);
		System.out.println("PeopleDetails set in list inside provider app::::" + pd1.toString());
		// Conversion of Object to JSON
		
		try {
			// I am converting Object to JSON value to send to Consumer Application
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValueAsString(pd1);// For single object to JSON conversion
			//For List object conversion
			// Let your list object name is lstVal
			//mapper.writeValueAsString(lstVal);// For List(Multiple value) object to JSON conversion
			
			
		} catch (Exception e) {
			System.out.println("Exception Occured while converting ModelObject to JSON");
		}
		
		return new ResponseEntity<PeopleDetails>(pd1, HttpStatus.OK);
	}
f
}
