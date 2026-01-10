package com.sst.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CollectDetailsService {
	
	@Autowired
	LoadBalancerClient loadBalancerClient;
	
	public String getStudentDetails() {
		// Get the single instance of producer service with less Load Factor from Eureka server
		// This below Student-Details is must same as producer service spring.application.name value of properties file
		ServiceInstance instance = loadBalancerClient.choose("Student-Details");
		/* If you remember in ms-proj1-consumer-service Discovery Client getInstance() method return type is List type out of that manually
		   we need to pick one instance by keeping index number and we have not sure weather that instance is minimum load factor or not.
		   But here Load balancing client automatically will pick minimum load factor instance.
		*/
		
		// Get the base URI of producer service
		URI uri = instance.getUri();
		
		// Make complete URL for producer service.
		//Here student is global variable and personalDetails is method variable of producer service.
		String url = uri.toString().concat("/students").concat("/personalDetails");
		
		// Create object of RestTemplate to call producer service method.
		RestTemplate template = new RestTemplate();
		
		// Call the respected method and get details of that method
		ResponseEntity<String> forEntity = template.getForEntity(url, String.class);
		
		// Output details of producer service method
		String output = forEntity.getBody();
		System.out.println("Output Details of Producer Service getStudentDetails() in Consumer Service::::::"+output);
		
		return output;
	}
}
