package com.sst.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*
 * In MicroService to communicate between two service we use Eureka service.
 * Eureka service create a base to communicate for all registered service with in it.
 * To communicate with other service via Eureka server we need client help.
 * 
 * There are generally 3 type of clients
 * 		1. DiscoveryClient(Legacy)(Maximum thing we need to do manually)
 * 		2. LoadBalancingClient
 * 		3. FiegnClient   
 * 
 * By using client object we will collect the instance and URI/URL of other service from Eureka service.
 * After collect all details we will use RestTemplate to send or receive information from other services.
 * 
 * Here we will learn the Discovery Client.
 * 
 */

@Service
public class ConsumerService {
	
	// Creating object of DiscoveryClient
	@Autowired
	private DiscoveryClient client;
	
	
	// These are just for information
	@Value("${spring.application.name}")
	String consumerApp;
	
	// These are just for information
	@Value("${server.port}")
	String serverPort;

	public String getProviderDetsils(){
		System.out.println("This is "+consumerApp+"and running in port no::::"+serverPort);
		// Get Instance of provider service. It's return type is List
		List<ServiceInstance> instances = client.getInstances("ms-proj1-provider-service");
		// Get one instance among List.
		ServiceInstance serviceInstance = instances.get(0);
		// Collect the base URI of provider service
		URI uri = serviceInstance.getUri();
		// Make the URL with help of URI
		String url = uri.toString()+"/provider"+"/providerDetails";
		
		// Created RestTemplate object to communicate with Provider Service.
		RestTemplate template = new RestTemplate();
		// Call the method of Provider Service.
		ResponseEntity<String> exchange = template.exchange(url, HttpMethod.GET, null, String.class);
		String result = exchange.getBody();
		System.out.println("Response from Provider Aervice::::"+result);
		return result;
	}
	

}
