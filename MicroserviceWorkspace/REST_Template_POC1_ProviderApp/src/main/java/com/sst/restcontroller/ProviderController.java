package com.sst.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {

	@GetMapping("/getProviderMsg")
	public ResponseEntity<String> getProviderMessage(){
		return new ResponseEntity<String>("Hi, This is REST Template Provider App response message....", HttpStatus.OK);
	}
	
}
//This project will render the information. So it called provider application.
//This URL http://localhost:8081/RestTemplateProviderApp/provider/getProviderMsg called API which provide information.
//This above API we will use in other application to get details from provider application. 