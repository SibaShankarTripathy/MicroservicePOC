package com.sst.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()//Why we use this and how to use understand first.
//@RequestMapping() //This is called Global Path and this is an Optional.
//@RequestMapping("*") //Similar to upper line but will accept all, no restriction
@RequestMapping("/message-cls") //This is proper way to route specific classes method
public class WelcomeMsg {

	@GetMapping("/msg")
	public ResponseEntity<String> getWelcomeMessage(){
		String msgVal = null;
		LocalDateTime tme = LocalDateTime.now();
		int hr = tme.getHour();
		msgVal = (hr<12?"Hi welcome to breakfast":hr<17?"Hi Welocme to Lunch":hr<21?"Welcome for snacks":"Welcome For Dinner");
		return new ResponseEntity<String>(msgVal,HttpStatus.OK);
	}
}


/*
 * If you use Run as Spring Boot Application OR Run as Java Application.
 * http://localhost:8080/message-cls/msg
 * -----------------------------------------------------------------
 * ResponseEntity<String> : - If response entity generic contain String means return will 
 * in TEXT format.
 * ResponseEntity<String> : - If response entity generic contain any type Object like list, map
 * then return type will JSON format.
 *
 */

