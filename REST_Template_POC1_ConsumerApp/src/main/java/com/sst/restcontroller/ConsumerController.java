package com.sst.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	//Consumer Application can be a stand alone or web application.
	//For testing purpose you can keep below logic in runner class and execute also.
	//For batter understand I used this below method to call Provider's method. Using UI screen.
	@GetMapping("/getProviderDetails")
	public ResponseEntity<String> getProviderDetails() {
		String consumerAppResponse = "";

		// Provider application URL. This URL should be correct otherwise it will not work.
		String providerUrlPath = "http://localhost:8081/RestTemplateProviderApp/provider/getProviderMsg";

		// Manual initialization of RestTemplate object. You can also use @Bean for initialization
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> responseFromProvider = null;
		try {
			// Calling of another application method using API with return type, by using getForEntity() method.
			// We can use 2 important method getForEntity() and getForObject().
			// getForEntity() return is ResponseEntity as response with HTTP Status.
			// getForObject() return is StringType as response without HTTP Status.
			responseFromProvider = rt.getForEntity(providerUrlPath, String.class);

			System.out.println("========== Welcome to Consumer Application =========");
			System.out.println("These below details are from Provider App responses");
			System.out.println("Providers actual response::::" + responseFromProvider.getBody());
			
			// This getStatusCodeValue() is deprecated. So we will use getStatusCode()
			System.out.println("Providers actual response code::::" + responseFromProvider.getStatusCodeValue());

			// We can get status code by multiple ways as shown below:
			System.out.println("Providers actual status code::::" + responseFromProvider.getStatusCode());
			HttpStatusCode statusCode = responseFromProvider.getStatusCode();
			
			// Way-1
			String ststusCodeVal = statusCode.toString();
			System.out.println("Status Code and HttpStatus::::" + ststusCodeVal);
			System.out.println("Ststus Code::::" + ststusCodeVal.substring(0, 3));
			System.out.println("Http Ststus::::" + ststusCodeVal.substring(4, ststusCodeVal.length()));
			
			// Way-2
			int value = statusCode.value();
			System.out.println("Ststus Code:--:"+value);

			if (statusCode.value() == 200) {
				consumerAppResponse = responseFromProvider.getBody();
			}
			
			System.out.println("------------------------------------------------------------------------------------------");
			String responseFromProviderAsObject = rt.getForObject(providerUrlPath, String.class); // Use of getForObject()
			System.out.println("Response from provider ============= "+responseFromProviderAsObject);
		} catch (Exception e) {
			System.out.println("Exception occured while calling provider app url.....");
			consumerAppResponse = "There is no response from Provider App. It may be stopped or incorrect url";
		}

		return new ResponseEntity<String>(consumerAppResponse, HttpStatus.OK);
	}
}
