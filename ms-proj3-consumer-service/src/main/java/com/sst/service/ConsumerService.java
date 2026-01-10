package com.sst.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/* 
 * This Interface is used to call Producer Service methods.
   So we use @FeignClient annotation and this "Producer-Service" should be same as 
   Producer Service's spring.application.name=Producer-Service available in properties file.
*/

@FeignClient("Producer-Service")
public interface ConsumerService {
	
	/* 
	 * Here we need to mention global path + method path
	   This method name you can change. Need not to same as Producer Service method name but
	   return type and method parameter should be same as that method.
	*/
	@GetMapping("/student-api/getStudentDetails")
	public ResponseEntity<String> getStudentDetails();
	
	/*
	 * Feign Client is a combination of Client + RestTemplate so we no need to write the RestTemplate logic
	   with Feign Client. It will collect less load factor instance and execute called method.
	 * It create in memory proxy class to execute all logic. Means in RAM it will create necessary class
	   and execute the logic. There only it will execute RestTemplate work also.
	 * If We use Discovery Client or Load Balancing Client then we need Rest Template separate to call the
	   Producer Service necessary methods.
	 */

}
