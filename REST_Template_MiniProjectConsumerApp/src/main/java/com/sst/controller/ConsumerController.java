package com.sst.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sst.model.PeopleDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/*
 * SWAGGER DETAILS:::
 * In this mini project I implemented swagger.
 * To implement swagger we need only one dependency named as "springdoc-openapi-starter-webmvc-ui" new version
 * This spring boot application is 3.5.7 version and it is compatibility with springdoc-openapi-starter-webmvc-ui.
 * If you are using spring boot 2.x.x then use this swagger version named as "springdoc-openapi-ui"
 * To get access the swagger link then use base url/swagger-ui/index.html. Ex: http://localhost:8083/RestMiniProjectConsumerApp/swagger-ui/index.html#
 * By default in swagger page your project name similar as your controller name with lower case.
 * 
 */



@RestController
@RequestMapping("/consumerApp")
@Tag(name="ConsumerApp", description="This controller used to CRUD operation for consumer")
//name is used for your controller name. description is used to describe your controller work.
public class ConsumerController {

	//To use this see main method
	@Autowired
	RestTemplate template;

	// Here I am not using runner class so I will create methods to hit from browser which will communicate to provider application methods
	// http://localhost:8083/RestMiniProjectConsumerApp/consumerApp/executePplSaveMethod
	@GetMapping("/executePplSaveMethod")
	@Operation(summary = "save people details", description = "save new people details in DB")//Swagger tag
	// @Opeartion at method level is similar to @Tag at class level
	// summary is similar to name.
	@ApiResponse(responseCode = "201", description = "successfully saved people details")//swagger tag
	//Open swagger URL to see these description.
	public ResponseEntity<String> insertPeopleDetails() {
		String saveUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/saveDetails";

		// To save people details I will create a dummy JSON format information as shown below
		String peopleDetails_json = "{\"pplName\":\"Jick\",\"ppladdress\":\"Boston\",\"pplAge\":28,\"pplPhone\":8521368590}";

		// To set request data is JSON type so we need this header object.
		HttpHeaders headers = new HttpHeaders();

		// Set send data type is JSON
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create Http request object type to send provider application
		HttpEntity<String> requestObj = new HttpEntity<String>(peopleDetails_json, headers);

		// Now your object and URL is ready to communicate with provider application API.
		// You can use GET, POST, PUT, PATCH, and DELETE methods OR can use exchange() for all type operation.

		// Use of POST
		ResponseEntity<String> postForEntityResonse = template.postForEntity(saveUrl, requestObj, String.class);
		System.out.println("Response of postForEntity method::::" + postForEntityResonse.getBody());
		System.out.println("StatusCode of postForEntity method::::" + postForEntityResonse.getStatusCodeValue());

		// Alternate way of POST using exchange method()
		// exchangeMethod(request_URL, HttpMethod_Type, HttpEntityRequestBody, Expected result/response)
		ResponseEntity<String> exchangeResponse = template.exchange(saveUrl, HttpMethod.POST, requestObj, String.class);

		System.out.println("Response of exchange method using POSt::::" + exchangeResponse.getBody());
		System.out.println("StatusCode of exchange method using POSt::::" + exchangeResponse.getStatusCodeValue());
		return new ResponseEntity<String>("People Details Saved.....", HttpStatus.OK);
	}

	// Now we will perform CRUD operation
	// C - Create
	@GetMapping("/executeCreateMethod")
	public ResponseEntity<String> savePeopleDetails() {
		System.out.println("========= Consumer Application ==========");
		System.out.println("========== Save People Details Method =========");
		String saveAPI = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/saveDetails";

		String peopleDetails_json = "{\"pplName\":\"Jick\",\"ppladdress\":\"Boston\",\"pplAge\":28,\"pplPhone\":8521368590}";

		// To set request data is JSON type so we need this header object.
		HttpHeaders headers = new HttpHeaders();

		// Set send data type is JSON
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create HTTP request object type to send provider application
		HttpEntity<String> requestObj = new HttpEntity<String>(peopleDetails_json, headers);

		// Use of POST
		ResponseEntity<String> postForEntityResonse = template.postForEntity(saveAPI, requestObj, String.class);
		System.out.println("Response of postForEntity method::::" + postForEntityResonse.getBody());
		System.out.println("StatusCode of postForEntity method::::" + postForEntityResonse.getStatusCode());

		// Use of exchange() with POST type
		ResponseEntity<String> exchangeResponse = template.exchange(saveAPI, HttpMethod.POST, requestObj, String.class);
		System.out.println("Response of exchange method using POSt::::" + exchangeResponse.getBody());
		System.out.println("StatusCode of exchange method using POSt::::" + exchangeResponse.getStatusCodeValue());

		return new ResponseEntity<String>("People Details Saved.....", HttpStatus.OK);
	}

	// R -- Retrieve
	// http://localhost:8083/RestMiniProjectConsumerApp/consumerApp/executeRetrieveMethod
	@GetMapping("/executeRetrieveMethod")
	public ResponseEntity<String> getPeopleDetails() {

		System.out.println("========= Consumer Application ==========");
		System.out.println("====== You are under getPeopleDetails() ==========");
		// Retrieve all people details
		String allPeopleDetailsUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/getAllPeopleDetails";

		// To use getForEntity we do not need HttpHeaders object. We need url and response data type.
		ResponseEntity<?> getForEntityResponse = template.getForEntity(allPeopleDetailsUrl, List.class);
//		ResponseEntity<?> getForEntityResponse = template.getForEntity(allPeopleDetails, String.class);//This will also work
		System.out.println("All people details using getForEntity()::::" + getForEntityResponse.getBody());
		System.out.println("Status Details using getForEntity()::::" + getForEntityResponse.getStatusCode());

		System.out.println("----------------------------------------------------------------------------------");
		// We will retrieve all details by using exchange()

		// For exchange() we must need HttpEntity for that we must need HttpHeaders.
		HttpHeaders headers = new HttpHeaders();
		// As per required we can add multiple headers also
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestObject = new HttpEntity<String>(headers);

		ResponseEntity<List> exchangeMethodResponse = template.exchange(allPeopleDetailsUrl, HttpMethod.GET, null,
				List.class);
		System.out.println("All people details using exchange():::::" + exchangeMethodResponse.getBody());
		System.out.println("Status Details using exchange():::::" + exchangeMethodResponse.getStatusCode());
		return new ResponseEntity<String>("All details retrieved successfully....", HttpStatus.OK);

	}

	// http://localhost:8083/RestMiniProjectConsumerApp/consumerApp/executeGetPplDtlsByPhnNumAndAge
	@GetMapping("/executeGetPplDtlsByPhnNumAndAge")
	public ResponseEntity<String> getPplDtlsByPhnNumAndAge() {
		// Example of single parameter value where Provider application used @RequestParam
		// This phoneNum variable spelling must match provider application method parameter variable spelling.
//		String peopleDetailsByPhnUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/getPplDtlsByPhn?phoneNum={pplPhone}";

		// Example of multiple parameter structure where in provider application used @RequestParam
		String peopleDetailsByPhnUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/getPplDtlsByPhn?phoneNum={phNum}&pplAge={age}";

		// Multiple parameter we can send two way either enter individual parameter value in order wise.
		// OR we can also sent the multiple parameter value using HashMap without follow order as shown below.
		Map<String, Object> uriParams = new HashMap<>();
		uriParams.put("age", 28);// This age spelling must be same as parameter condition {age}.
		uriParams.put("phNum", 9876543210L);// This phNum spelling must be same as parameter condition {phNum}.
		// This above Hash map object we can use both getForEntity() and exchange()

		Long phNum = 1234567890L;
		Integer age = 25;
		ResponseEntity<String> getForEntityResponse = template.getForEntity(peopleDetailsByPhnUrl, String.class, phNum, age);
//		ResponseEntity<String> getForEntityResponse = template.getForEntity(peopleDetailsByPhnUrl, String.class, uriParams);
		System.out.println("Single people details using getForEntity()::::" + getForEntityResponse.getBody());
		System.out.println("Status Details using getForEntity()::::" + getForEntityResponse.getStatusCode());

		System.out.println("----------------------------------------------------------------------------------");
		// We will retrieve all details by using exchange()

		// Example of passing single parameter
//		ResponseEntity<String> exchangeMethodResponse = template.exchange(peopleDetailsByPhnUrl, HttpMethod.GET, null, String.class,phNum);

		// Example of passing multiple parameter
//		ResponseEntity<String> exchangeMethodResponse = template.exchange(peopleDetailsByPhnUrl, HttpMethod.GET, null, String.class,phNum,age);// Using variable in order
		ResponseEntity<String> exchangeMethodResponse = template.exchange(peopleDetailsByPhnUrl, HttpMethod.GET, null, String.class, uriParams);// Using hash map object without follow order
		System.out.println("Single people details using exchange():::::" + exchangeMethodResponse.getBody());
		System.out.println("Status Details using exchange():::::" + exchangeMethodResponse.getStatusCode());
		return new ResponseEntity<String>("All details retrieved successfully....", HttpStatus.OK);

	}

	// http://localhost:8083/RestMiniProjectConsumerApp/consumerApp/executeGetPplAddrByPhnNumAndNme
	@GetMapping("/executeGetPplAddrByPhnNumAndNme")
	public ResponseEntity<String> getPplAddrByPhnNumAndNme() {
		// Example of single parameter value where Provider application used @PathVariable
//		String peopleDetailsByPhnUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/getPplAddrs/{name}";

		Long phone = 1234567890L;
		String name = "Mike";

		// Example of multiple parameter structure where in provider application used @PathVariable
		String peopleDetailsByPhnAndNmeUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/getPplAddrs/{nameee}/{phoneee}";

		// Multiple parameter we can send two way either enter individual parameter value in order wise.
		// OR we can also sent the multiple parameter value using HashMap without follow order as shown below.
		Map<String, Object> uriParams = new HashMap<>();
		uriParams.put("nameee", name );// This nameee spelling must be same as parameter condition /{nameee}/.
		uriParams.put("phoneee", phone);// This phoneee spelling must be same as parameter condition /{phoneee}/.
		// This above Hash map object we can use both getForEntity() and exchange()

//		ResponseEntity<String> getForEntityResponse = template.getForEntity(peopleDetailsByPhnAndNmeUrl, String.class, name, phone);
		ResponseEntity<String> getForEntityResponse = template.getForEntity(peopleDetailsByPhnAndNmeUrl, String.class, uriParams);
		System.out.println("Single people details using getForEntity()::::" + getForEntityResponse.getBody());
		System.out.println("Status Details using getForEntity()::::" + getForEntityResponse.getStatusCode());

		System.out.println("----------------------------------------------------------------------------------");
		// We will retrieve all details by using exchange()

		// Example of passing single parameter
//		ResponseEntity<String> exchangeMethodResponse = template.exchange(peopleDetailsByPhnAndNmeUrl, HttpMethod.GET, null, String.class, name);

		// Example of passing multiple parameter
		ResponseEntity<String> exchangeMethodResponse = template.exchange(peopleDetailsByPhnAndNmeUrl, HttpMethod.GET, null, String.class,name, phone);// Using variable in order
//		ResponseEntity<String> exchangeMethodResponse = template.exchange(peopleDetailsByPhnAndNmeUrl, HttpMethod.GET, null, String.class, uriParams);// Using hash map object without follow order
		System.out.println("Single people details using exchange():::::" + exchangeMethodResponse.getBody());
		System.out.println("Status Details using exchange():::::" + exchangeMethodResponse.getStatusCode());
		return new ResponseEntity<String>("All details retrieved successfully....", HttpStatus.OK);

	}
	
	/*
	 * In REST by default request and response are JSON format.
	 * Let there is an requirement one of API like normal bean object instead of JSON then we need this below concept.
	 * Here we will learn two things one is JSON -> Java Object(Serialization Process) and two is Java Object -> JSON(DeSerialization Process)
	 * To achieve this we need JACKSON API concept and need to keep that required Java bean object in both Provider and Consumer APP.
	 * For JACKSON API no need for separate dependency because it is included in spring-web-starter
	 * Here our bean object is People Details so we will keep this in both consumer and provider app.
	 * Carefully observe every line in below 2 method like retrieve for single details and List of details
	 * For Object to JSON conversion we need to make method in Provider App for testing purpose and taht method we will call here.
	 */
	// JSON to Java Object
	
	// R -- Retrieve
	// http://localhost:8083/RestMiniProjectConsumerApp/consumerApp/executeRetrieveMethod1
	@GetMapping("/executeRetrieveMethod1")
	public ResponseEntity<String> getAllPeopleDetails1() {

		System.out.println("========= Consumer Application ==========");
		System.out.println("====== You are under getPeopleDetails1() ==========");
		System.out.println("====== Here we will see JSON to Object convertion ==========");
		// Retrieve all people details
		String allPeopleDetailsUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/getAllPeopleDetails";

		ResponseEntity<String> getForEntityResponse = template.getForEntity(allPeopleDetailsUrl, String.class);
		System.out.println("All people details in JSON format using getForEntity()::::" + getForEntityResponse.getBody());
		System.out.println("Status Details using getForEntity()::::" + getForEntityResponse.getStatusCode());
		
		// JSON to Java Object
		try {
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<PeopleDetails> peopleDetailsValue = mapper.readValue(getForEntityResponse.getBody(), new TypeReference<ArrayList<PeopleDetails>>() {});
			//PeopleDetails peopleDetailsValue = mapper.readValue(getForEntityResponse.getBody(), PeopleDetails.class);//This line will use for single object value
			System.out.println("People Details in Model Object format:::::::"+peopleDetailsValue);
		} catch (Exception e) {
			System.out.println("Exception occured while converting JSON data to model Object");
		}
		

		System.out.println("----------------------------------------------------------------------------------");
		// We will retrieve all details by using exchange()

		// For exchange() we must need HttpEntity for that we must need HttpHeaders.
		HttpHeaders headers = new HttpHeaders();
		// As per required we can add multiple headers also
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestObject = new HttpEntity<String>(headers);

		ResponseEntity<List> exchangeMethodResponse = template.exchange(allPeopleDetailsUrl, HttpMethod.GET, null,
				List.class);
		System.out.println("All people details using exchange():::::" + exchangeMethodResponse.getBody());
		System.out.println("Status Details using exchange():::::" + exchangeMethodResponse.getStatusCode());
		return new ResponseEntity<String>("All details retrieved successfully....", HttpStatus.OK);

	}
	
		// R -- Retrieve
		// http://localhost:8083/RestMiniProjectConsumerApp/consumerApp/executeRetrieveMethod2
		@GetMapping("/executeRetrieveMethod2")
		public ResponseEntity<String> getSinglePeopleDetails() {

			System.out.println("========= Consumer Application ==========");
			System.out.println("====== You are under getSinglePeopleDetails() ==========");
			System.out.println("====== Here we will see JSON to Object convertion ==========");
			// Retrieve all people details
			String allPeopleDetailsUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/getSinglePeopleDetails";//This line for single Object retrieve

			ResponseEntity<String> getForEntityResponse = template.getForEntity(allPeopleDetailsUrl, String.class);
			System.out.println("Single people details in JSON format using getForEntity()::::" + getForEntityResponse.getBody());
			System.out.println("Status Details using getForEntity()::::" + getForEntityResponse.getStatusCode());
			
			// JSON to Java Object
			try {
				ObjectMapper mapper = new ObjectMapper();
				PeopleDetails peopleDetailsValue = mapper.readValue(getForEntityResponse.getBody(), PeopleDetails.class);//This line will use for single object value
				System.out.println("Single People Details in Model Object format:::::::"+peopleDetailsValue);
			} catch (Exception e) {
				System.out.println("Exception occured while converting JSON data to model Object");
			}
			

			System.out.println("----------------------------------------------------------------------------------");
			// We will retrieve all details by using exchange()

			//For exchange() we must need HttpEntity for that we must need HttpHeaders.
			HttpHeaders headers = new HttpHeaders();
			// As per required we can add multiple headers also
			headers.setContentType(MediaType.APPLICATION_JSON);
	
			HttpEntity<String> requestObject = new HttpEntity<String>(headers);
	
			ResponseEntity<PeopleDetails> exchangeMethodResponse = template.exchange(allPeopleDetailsUrl, HttpMethod.GET, null,
					PeopleDetails.class);
			System.out.println("All people details using exchange():::::" + exchangeMethodResponse.getBody());
			System.out.println("Status Details using exchange():::::" + exchangeMethodResponse.getStatusCode());
			return new ResponseEntity<String>("All details retrieved successfully....", HttpStatus.OK);

		}
		
		// R -- Retrieve
		// http://localhost:8083/RestMiniProjectConsumerApp/consumerApp/executeRetrieveMethod2
		@GetMapping("/executeRetrieveMethod3")
		public ResponseEntity<String> getSinglePeopleDetails1() {

			System.out.println("========= Consumer Application ==========");
			System.out.println("====== You are under getSinglePeopleDetails1() ==========");
			System.out.println("====== To understand this method purpose see Provider app getSinglePeopleDetailsObjFrmt() ==========");
			// Retrieve all people details
			String allPeopleDetailsUrl = "http://localhost:8081/RESTTemplateMiniProject/peopleDetails/getSinglePeopleDetailsObjFrmt";// This line for single Object retrieve

			ResponseEntity<String> getForEntityResponse = template.getForEntity(allPeopleDetailsUrl, String.class);
			System.out.println("Single people details in JSON format using getForEntity()::::" + getForEntityResponse.getBody());
			System.out.println("Status Details using getForEntity()::::" + getForEntityResponse.getStatusCode());


			System.out.println("----------------------------------------------------------------------------------");
			// We will retrieve all details by using exchange()

			// For exchange() we must need HttpEntity for that we must need HttpHeaders.
			HttpHeaders headers = new HttpHeaders();
			// As per required we can add multiple headers also
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> requestObject = new HttpEntity<String>(headers);

			ResponseEntity<PeopleDetails> exchangeMethodResponse = template.exchange(allPeopleDetailsUrl,
					HttpMethod.GET, null, PeopleDetails.class);
			System.out.println("All people details using exchange():::::" + exchangeMethodResponse.getBody());
			System.out.println("Status Details using exchange():::::" + exchangeMethodResponse.getStatusCode());
			return new ResponseEntity<String>("All details retrieved successfully....", HttpStatus.OK);

		}

}
