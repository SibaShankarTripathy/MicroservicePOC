package com.sst.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StandAloneConsumer {

	public static void main(String[] args) {
		String consumerAppResponse = "";
		// Provider application URL. This URL should be correct otherwise it will not work.
		String providerUrlPath = "http://localhost:8081/RestTemplateProviderApp/provider/getProviderMsg";

		RestTemplate rt = new RestTemplate();
		try {
			ResponseEntity<String> responseFromProvider = rt.getForEntity(providerUrlPath, String.class);
			consumerAppResponse = responseFromProvider.getBody();
			System.out.println("Providers actual status code::::" + responseFromProvider.getStatusCode());
		} catch (Exception e) {
			System.out.println("Exception occured while calling provider app url.....");
			consumerAppResponse = "There is no response from Provider App. It may be stopped or incorrect url";
		}
		System.out.println("Providers Response::::" +consumerAppResponse);
	}
}
/*
 * This is a stand alone java application.
 * In this application we are hitting external URL / API.
 * To use external URL which is designed in REST we need some most important jar file in our class path.
 * Jars are:
 * 	spring-core
 * 	spring-web
 * 	commons-logging(Apache)
 * If that REST application design using spring 6.0.0 above version then we need these below jar also. 
 * 	micrometer-commons
 * 	micrometer-observation
 *  
 * You need to add these above jars in build path using Add External jars option of build path option under classpath. 
 *  
 *  */
