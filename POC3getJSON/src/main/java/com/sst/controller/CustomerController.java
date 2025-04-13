package com.sst.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.model.CustomerDetails;
import com.sst.model.ProductDetails;

// As we know the Response Entity Generic type is other then String then it becomes JSON format in return type.
// In this Controller we will sent JSON object to view screen.
// Also we will sent List of Object in JSON format.

@RestController
@RequestMapping("/custDetails")
public class CustomerController {

	// This method will return one Customer Details in JSON format.
	// http://localhost:8080/custDetails/singleRec
	@GetMapping("/singleRec")
	public ResponseEntity<CustomerDetails> getCustomerDetails() {
		CustomerDetails cust = new CustomerDetails();
		cust.setName("Ritesh");
		cust.setAge(20);
		return new ResponseEntity<CustomerDetails>(cust, HttpStatus.OK);
		// Instead of Response Entity return type we can sent Customer Details object
		// but we can not set Status code in return type;
	}

	// This method will return multiple Details in JSON format.
	// http://localhost:8080/custDetails/multiRec
	@GetMapping("/multiRec")
	public ResponseEntity<List> getMultiCustomerDetails() {

		List<CustomerDetails> custList = new ArrayList<>();
		CustomerDetails cust = new CustomerDetails();
		cust.setName("Ritesh");
		cust.setAge(20);
		custList.add(cust);

		List<ProductDetails> prdList = new ArrayList<>();
		ProductDetails prdDetails = new ProductDetails();
		prdDetails.setPrdName("Computer");
		prdDetails.setPrdPrice(45000.69f);
		prdList.add(prdDetails);

		List<Object> allDetails = new ArrayList<>();
		allDetails.add(custList);
		allDetails.add(prdList);

		return new ResponseEntity<List>(allDetails, HttpStatus.OK);
	}
}
