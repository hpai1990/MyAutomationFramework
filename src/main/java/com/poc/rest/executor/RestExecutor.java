package com.poc.rest.executor;

import static io.restassured.RestAssured.given;

import com.poc.rest.validator.RestValidator;
import com.poc.utils.RequestBuilder;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class RestExecutor {
	
		
	public static synchronized RestValidator get(RequestBuilder spec){
		
		Response response = given().
			filter(new AllureRestAssured()).
			spec(spec.getRequestSpecification()).
			config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).
		when().
			get(spec.getRequestBasePath());
		
				
		return (new RestValidator(parseResponse(response)));
		
	}
	
	public static synchronized RestValidator post(RequestBuilder spec,String data){
		
		Response response = given().
			filter(new AllureRestAssured()).
			spec(spec.getRequestSpecification()).
			body(data.toString()).
			config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).
		when().
			post(spec.getRequestBasePath());
		
		return (new RestValidator(parseResponse(response)));
		
	}
	
	private static synchronized RestResponse parseResponse(Response response){
		
		RestResponse resResponse = new RestResponse();
		resResponse.setResponseBody(response.getBody().asString()); 
		resResponse.setResponseCode(response.getStatusCode());
		Headers headers = response.getHeaders();
		for (Header header : headers) {
			resResponse.setHeader(header.getName(), header.getValue());
		}
		
		return resResponse;
	}

}
