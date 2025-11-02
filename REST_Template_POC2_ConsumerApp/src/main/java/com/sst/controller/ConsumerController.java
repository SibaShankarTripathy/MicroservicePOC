package com.sst.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@GetMapping("/consumerPeopleDetails")
	public ResponseEntity<String> executeToGetPeopleDetails(){
		String saveUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/saveDetails";
		RestTemplate template = new RestTemplate();
		// To save people details I will create a dummy JSON format information as shown below
		String peopleDetails_json = "\"pplName\":\"Jick\",\"ppladdress\":\"Boston\",\"pplAge\":28,\"pplPhone\":8521368590";
		
		//For request object we need this header object to set send data type is JSON
		HttpHeaders headers = new HttpHeaders();
		// Set send data type is JSON
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Create request object to send provider application
		HttpEntity<String> requestObj = new HttpEntity<String>(peopleDetails_json,headers);
		
		ResponseEntity<String> postForEntityResonse = template.postForEntity(saveUrl, requestObj, String.class);
		
		
	}
}
