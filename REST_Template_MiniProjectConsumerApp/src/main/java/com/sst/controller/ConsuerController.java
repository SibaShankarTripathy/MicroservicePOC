package com.sst.controller;

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

@RestController
@RequestMapping("/consumerApp")
public class ConsuerController {

	//To use this see main method
	@Autowired
	RestTemplate template;

	// Here I am not using runner class so I will create methods to hit from browser which will communicate to provider application methods
	// http://localhost:8083/RestMiniProjectConsumerApp/consumerApp/executePplSaveMethod
	@GetMapping("/executePplSaveMethod")
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

}
