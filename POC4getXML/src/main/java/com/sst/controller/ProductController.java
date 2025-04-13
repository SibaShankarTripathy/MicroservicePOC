package com.sst.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sst.model.ProductDetails;

//As we know the Response Entity Generic type is other then String then it becomes JSON format in return type.
//In this Controller we will sent XML object to view screen.
//Also we will sent List of Object in XML format.
//By default return type is JSON but we will convert JSON to XML by using XML dependency known as jackson-dataformat-xml

@RestController
@RequestMapping("/prodDetails")
public class ProductController {

	// This method will return one Customer Details in XML format.
	// http://localhost:8080/prodDetails/singleRec
	@GetMapping("/singleRec")
	public ResponseEntity<List> getCustomerDetails() {
		
		List<ProductDetails> prdList = new ArrayList<>();
		ProductDetails prdDetails = new ProductDetails();
		prdDetails.setPrdName("Computer");
		prdDetails.setPrdPrice(45000.69f);
		prdList.add(prdDetails);
		
		return new ResponseEntity<List>(prdList, HttpStatus.OK);
	}

	// This method will return multiple Details in XML format.
	// http://localhost:8080/custDetails/multiRec
	@GetMapping("/multiRec")
	public ResponseEntity<List> getMultiCustomerDetails() {

		List<ProductDetails> prdList = new ArrayList<>();
		ProductDetails prdDetails = new ProductDetails();
		prdDetails.setPrdName("Computer");
		prdDetails.setPrdPrice(45000.69f);
		prdList.add(prdDetails);
		
		prdDetails.setPrdName("Printer");
		prdDetails.setPrdPrice(15000.69f);
		prdList.add(prdDetails);

		List<Object> allDetails = new ArrayList<>();
		allDetails.add(prdList);

		return new ResponseEntity<List>(allDetails, HttpStatus.OK);
	}
}
